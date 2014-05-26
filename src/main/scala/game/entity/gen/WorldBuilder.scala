package game.entity.gen

import game.entity.Family
import game.entity.Location
import game.entity.World
import game.entity.data.LocationNames
import game.random.Bag
import game.tools.WordGenerator
import game.entity.Character
import game.entity.data.CharacterNames

class WorldBuilder {

  var noChars: Int = 100;
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

  def generateWorld: World = {
    val world = new World();

    initWorld(world)

    world;
  }

  private def initWorld(world: World) {
    val lastNameGen = new WordGenerator("data/last-names-3char-freqs.txt", 3)

    world.locations = 0 until noLocations map { nm =>
      val loc = new Location() {
        
        name = LocationNames.getName("", 4, 5)
        println("Location: " + name)
      };

      println("Families:")
      val fams = famsPerLocOdds.get
      for (famInd <- 0 until fams) {
        var famName = lastNameGen.getWord()
        famName = famName.head.toUpper + famName.tail
        
        val family = new Family()
        family.lastName = famName

        val famSize = famSizeOdds.get
        for (ch <- 0 until famSize) {
          val character = CharacterCreator.initCharacter
          character.location = Some(loc)
          if (family.heads.size < 2) {
            family.addHead(character)
          } else {
            family.addMember(character)
          }
        }
        
        println(family)
        println("\n\n")
      }

      println("\n\n----------------------------------------")
      loc
    }

    val sylBag = Bag(1 -> 10, 2 -> 20, 3 -> 20, 4 -> 20, 5 -> 10, 6 -> 3, 7 -> 1)

//    println("\n\n-------")
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
//    println(FamilyName.generateName(sylBag.get).name)
  }

}

object WorldBuilder {
  def main(args: Array[String]) {
    new WorldBuilder().generateWorld;
  }
}