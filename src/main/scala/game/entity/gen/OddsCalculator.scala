package game.entity.gen

class OddsCalculator[T](odds:(T,Int)*) {

    val total:Int = odds.map{case(item,chances) => chances}.sum
    
    val probs:Seq[(T,Double)] = {
      odds.map{case(item,chances) => 
          (item, chances.asInstanceOf[Double] / total.asInstanceOf[Double])
      }  
    }
    
    def getItem:T = {
      val rnd = math.random
      var cur = 0.0
      
      val itr = probs.iterator
      
      while(itr.hasNext && rnd >= cur){
          val item = itr.next();
          
          cur += item._2
          if(cur >= rnd){
              return item._1
          }
      }
      
      probs.last._1
    }
}