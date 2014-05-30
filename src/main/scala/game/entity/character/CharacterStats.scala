package game.entity.character

class CharacterStats {

  private var stats = Stat.values.map(stat => (stat,0)).toMap
  
  def apply(stat:Stat):Int = {
    stats(stat)
  }
  
  def update(stat:Stat,value:Int) = {
    import CharacterStats._
    stats = stats.updated(stat, math.min(MaxVal,math.max(MinVal,value)))
  }
  
  def avgLevel:Int = {
    val sum = this.stats.toSeq.map(entry => entry._2).sum.toDouble
    (sum / stats.size.toDouble).toInt
  }
  
}

object CharacterStats{
  val MaxVal = 255
  val MinVal = 0
}