package game.entity.gen
import game.entity.World
import game.entity.data.LocationNames
import game.entity.Location
import game.entity.Family
import game.random.Bag
import game.entity.FamilyName
import game.random.Random

class WorldBuilder {
    
    var noChars:Int = 100;
    var noLocations:Int = 10;
    
    val famSizeOdds = new OddsCalculator[Int](
    	0->8,
    	1->10,
    	2->8,
    	3->5,
    	4->3,
    	5->2,
    	6->1)
    	
    val famsPerLocOdds = new OddsCalculator[Int](
    	4->1,
    	6->2,
    	10->3,
    	15->2,
    	20->1)
    
    def generateWorld:World = {
        val world = new World();
        
        initWorld(world)
        
        world;
    }
    
    private def initWorld(world:World){
       world.locations = LocationNames.names.toSeq.map{nm =>
           new Location(){
               name = LocationNames.locPrefixes.getItem + nm + LocationNames.locSuffixes.getItem
               println(name)
           };
       }
       
       val things = Bag("Kisses", "Hugs", "etc.", "back rubs", "blindfold", "a big cock")
       println(things.get)
       println(things.get)
       println(things.get)
       println(things.get)
       println(things.get)
       
       val sylBag = Bag(1->10, 2->20, 3->20, 4->20, 5->10, 6->3, 7->1)
       
       println("\n\n-------")
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
       println(FamilyName.generateName(sylBag.get).name)
    }
   
}

object WorldBuilder {
    def main(args:Array[String]){
        new WorldBuilder().generateWorld;
    }
}