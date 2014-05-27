package game.entity.character

class CharacterStats {

  private var stats = StatType.values.map(dim => (dim,0)).toMap
  
  def apply(stat:StatType):Int = {
    stats(stat)
  }
  
  def update(stat:StatType,value:Int) = {
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