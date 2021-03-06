package game.entity.gen

import game.entity.Location
import game.entity.World
import game.entity.data.LocationNames
import game.random.Bag
import game.tools.WordGenerator
import game.entity.data.CharacterNames
import game.entity.character.Family
import game.entity.character.Character
import game.random.Random
import game.entity.event.LifeEvent
import game.entity.event.LifeEventType
import org.joda.time.DateTime
import game.entity.character.Relationship
import game.entity.character.RelationshipType
import game.entity.character.CharacterEventHelper
import game.entity.event.EventPlugins
import game.content.events.AnimalAttack
import game.entity.character.CharacterQuality
import game.entity.data.CharacterLevelRates
import game.entity.event.CharacterEvent
import scala.collection.immutable.HashSet
import game.tools.DateUtils._

class WorldBuilder {

  var noLocations: Int = 10;

  val famSizeOdds = Bag[Int](
    1 -> 8,
    2 -> 10,
    3 -> 8,
    4 -> 5,
    5 -> 3,
    6 -> 2,
    7 -> 1)

  val famsPerLocOdds = Bag[Int](
    4 -> 1,
    6 -> 2,
    10 -> 3,
    15 -> 2,
    20 -> 1)
  
  lazy val lastNameGen = new WordGenerator("data/last-names-3char-freqs.txt", 3)

  EventPlugins.registerSimilarPlugins(AnimalAttack);
  //EventPlugins.registerPlugin(AnimalAttack)

  def generateWorld: World = {
    val world = new World();

    initWorld(world)

    world;
  }

  def createFamily(size: Int, loc: Location): Family = {
    import LifeEventType._
    import RelationshipType._

    val world = loc.world.get
    var famName = lastNameGen.getWord().capitalize
    val family = new Family()
    family.lastName = famName
    for (ch <- 0 until size) {
      val character = CharacterCreator.initCharacter
      world.addCharacter(character)
      character.location = Some(loc)
      loc.addCharacter(character)

      if (family.heads.size < 2) {
        val birthDate = world.year.year().addToCopy(-20 - Random.rand.randInt(40))
        character.birthDate = Some(birthDate)
        family.addHead(character)
        CharacterEventHelper.birth(None, character)
      } else {
        val birthDate = world.year.year().addToCopy(-Random.rand.randInt(20))
        character.birthDate = Some(birthDate)
        CharacterEventHelper.birth(Some(family), character)
      }
    }

    //Set marriage relationship
    for (p1 <- family.head1; p2 <- family.head2) {
      val mDate = world.year.year().addToCopy(-Random.rand.randInt(20))
      CharacterEventHelper.marry(p1, p2, mDate, 20 + Random.rand.randInt(20, 255), Random.rand.randInt(20, 255))
    }

    family
  }

  private def initWorld(world: World) {
    world.year = world.year.year().addToCopy(200)

    world.locations = 0 until noLocations map { nm =>
      val loc = new Location() {
        name = LocationNames.getName("", 4, 5)
        println("Location: " + name)
      };
      loc.world = Some(world)

      println("Families:")

      for (
        fams <- famsPerLocOdds.get;
        famInd <- 0 until fams;
        famSize <- famSizeOdds.get
      ) {

        val family = createFamily(famSize, loc)

        println(family)
        println("\n\n")
      }

      println("\n\n----------------------------------------")
      loc
    }

    //Simulate the world for a number of years
    for (year <- 0 until 50) {
      
      println("***********************************************")
      println(s"SIMULATING WORLD, YEAR ${world.year.fmtDate()}, FOR ${world.livingCharacters.size} CHARACTERS")
      
      //Simulate events that only happen to a single character
      for (char <- world.livingCharacters) {
        
        for(lvls <- 0 until CharacterLevelRates.rates(char.age)){
        	char.levelUp
        }
        
        for (event <- EventPlugins.characterEvents) {
          if(event.isTriggered(char)){
	          event match {
	            case e:CharacterEvent => {
	              val lifeEvent = e.resolve(char, Some(world.year))
	              char.lifeEvents.add(lifeEvent)
	            }
	            case _ =>
	          }
          }
          
        }
      }
      
      //Simulate events that are relationship-based
      //First find all pairs of characters that are already in a relationship
      var pairs = HashSet[(Character,Character)]()
      for(char1 <- world.livingCharacters;
          char2 <- char1.relationships.all.map(_._1);
          if(!char2.isDeceased)){
        pairs += char1 -> char2
      }
      
      //Next find all pairs of characters who are not in a relationship, 
      //but are in the same location
      val locPairs = for(loc <- world.locations;
    	  char1 <- loc.livingCharacters;
    	  char2 <- loc.livingCharacters;
    	  if(char1 != char2)) yield{
        char1 -> char2
      }
      
      val nonRelLocPairs = locPairs.filter{ case (char1,char2) =>
        char1.relationships(char2).isEmpty
      }
      
      nonRelLocPairs.foreach{ pair =>
        pairs = pairs + pair
      }
      
      //Now, calculate all relationship events
      for(pair <- pairs;
          event <- EventPlugins.relationshipEvents){
        if(event.isTriggered(pair._1,pair._2)){
          event.resolve(pair._1,pair._2,Some(world.year))
        }
      }
      

      world.year = world.year.year().addToCopy(1)
    }

    for (ch <- world.characters) {
      println("Character: " + ch.basicInfo(true))
      println("Personality: ")
      println("  " + ch.personality.toString.replaceAll("\n", "\n  "))
      println("Stats: ")
      println("  " + ch.stats.toString.replaceAll("\n", "\n  "))
      println("Relationships: ")
      println("  " + ch.relationships.toString.replaceAll("\n", "\n  "))
      println("Life Events: ")
      println("  " + ch.lifeEvents.toString.replaceAll("\n", "\n  "))
      println("\n\n------------------------------------------")
    }
  }

}

object WorldBuilder {
  def main(args: Array[String]) {
    new WorldBuilder().generateWorld;
  }
}