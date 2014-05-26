package game.random

class Bag[T](val items:Seq[(T,Int)], val rnd:Random = Random.bagRand) {

    val total:Int = items.map{case(item,chances) => chances}.sum
    
    private val probs:Seq[(T,Double)] = {
      items.map{case(item,chances) => 
          (item, chances.asInstanceOf[Double] / total.asInstanceOf[Double])
      }  
    }
    
    def get:T = {
      val rndDbl = rnd.randDouble
      var cur = 0.0
      
      val itr = probs.iterator
      
      while(itr.hasNext && rndDbl >= cur){
          val item = itr.next();
          
          cur += item._2
          if(cur >= rndDbl){
              return item._1
          }
      }
      
      probs.last._1
    }
}

object Bag{
    
    def apply[T](items:(T,Int)*):Bag[T] = {
        new Bag[T](items)
    }
    
    def apply[T](item1:T, items:T*):Bag[T] = {
        val col:Seq[(T,Int)] = Seq((item1, 1)) ++
        	items.map(item => (item,1))
        new Bag[T](col)
    }
}