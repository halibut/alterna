package game.random
import java.util.Date

class Random(val seed: Long = new Date().getTime()) {

  val rand: java.util.Random = new java.util.Random(seed)

  def randInt(max: Int): Int = {
    rand.nextInt(max)
  }

  def randInt = {
    rand.nextInt()
  }

  def randDouble(max: Double): Double = {
    rand.nextDouble() * max
  }

  def randDouble: Double = {
    rand.nextDouble()
  }
  
  def hitOdds(chance:Int,outOf:Int):Boolean = {
    val limit = chance.toDouble / outOf.toDouble
    randDouble <= limit
  }

  private def seed(seed: Long): Unit = {
    rand.setSeed(seed);
  }

}

object Random {
  val rand = new Random()
  val bagRand = new Random()

}