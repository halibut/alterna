package game.entity.character

class CharacterStats {

  private var stats = Stat.values.map(stat => (stat,0)).toMap
  
  def apply(stat:Stat):Int = {
    stats(stat)
  }
  
  def update(stat:Stat,value:Int) = {
    stats = stats.updated(stat, math.min(stat.getMax(),math.max(stat.getMin(),value)))
  }
  
  def avgLevel:Int = {
    val sum = this.stats.toSeq.map(entry => entry._2).sum.toDouble
    (sum / stats.size.toDouble).toInt
  }
  
  
  override def toString():String = {
    stats.toSeq
    	.sortWith{case (s1,s2) => s1._2 > s2._2}
    	.map{case (stat,value) =>
    	  stat.name()+": "+value
    	}
    	.mkString("\n") +
        "\nAvg: "+avgLevel
  }
}
