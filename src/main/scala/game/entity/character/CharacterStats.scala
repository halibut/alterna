package game.entity.character

class CharacterStats {

  private var stats = StatType.values.map(dim => (dim,0)).toMap
  
  def apply(stat:StatType):Int = {
    stats(stat)
  }
  
  def update(stat:StatType,value:Int) = {
    stats = stats.updated(stat, math.min(CharacterStats.MaxVal,math.max(0,value)))
  }
  
}

object CharacterStats{
  val MaxVal = 255
}