package game.entity.character

class CharacterStats {

  private var _stats = Stat.values.map(stat => (stat,0)).toMap
  
  def apply(stat:Stat):Int = {
    _stats(stat)
  }
  
  def update(stat:Stat,value:Int) = {
    _stats = _stats.updated(stat, math.min(stat.getMax(),math.max(stat.getMin(),value)))
  }
  
  def stats:Seq[(Stat,Int)] = {
    this._stats.toSeq
  }
  
  def avgLevel:Int = {
    import Stat._
    val filteredStats = this._stats.toSeq
    		.filter(s => s._1 != MaxAP && s._1 != MaxHP)
    		.map(entry => entry._2)
    		
    
    val sum = filteredStats.sum.toDouble
    (sum / filteredStats.size.toDouble).toInt
  }
  
  
  override def toString():String = {
    _stats.toSeq
    	.sortWith{case (s1,s2) => s1._2 > s2._2}
    	.map{case (stat,value) =>
    	  stat.name()+": "+value
    	}
    	.mkString("\n") +
        "\nAvg: "+avgLevel
  }
}
