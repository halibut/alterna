package game.random
import java.util.Date

class Random(val seed:Long = new Date().getTime()) {

    val rand:java.util.Random = new java.util.Random(seed)
    
    def randInt(max:Int):Int = {
        rand.nextInt(max)
    }
    
    def randInt = {
        rand.nextInt()
    }
    
    def randDouble(max:Double):Double = {
        rand.nextDouble() * max
    }
    
    def randDouble:Double = {
        rand.nextDouble()
    }
    
    private def seed(seed:Long):Unit = {
        rand.setSeed(seed);
    }
    
}

object Random{
    val bagRand = new Random()
    
}