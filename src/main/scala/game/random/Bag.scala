package game.random

import scala.language.implicitConversions 

class Bag[T](items: Seq[(T, Double)], val rnd: Random = Random.bagRand) {
  
  
  val total: Double = items.map { case (item, chances) => chances }.sum

  private val probs:Seq[(T, Double)] = {
    items.map {
      case (item, chances) =>
        (item, chances / total)
    }
  }

  def get: Option[T] = {
    if(probs.isEmpty){
      return None
    }
    
    val rndDbl = rnd.randDouble
    var cur = 0.0

    val itr = probs.iterator

    while (itr.hasNext && rndDbl >= cur) {
      val item = itr.next();

      cur += item._2
      if (cur >= rndDbl) {
        return Some(item._1)
      }
    }

    Some(probs.last._1)
  }
}

object Bag {
  
  def apply[T](items: (T, Double)*): Bag[T] = {
    new Bag[T](items.map(i => (i._1 , i._2)))
  }
  
  def fromItems[T](items: T*):Bag[T] = {
    new Bag[T](items.map(i => (i, 1.0)))
  }
  
  def fromProbs[T](items: (T,Int)*): Bag[T] = {
    new Bag[T](items.map(i => (i._1, i._2.toDouble)))
  }
  
}