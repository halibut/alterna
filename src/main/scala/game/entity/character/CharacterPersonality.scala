package game.entity.character

import game.random.Random

class CharacterPersonality {

  private var dimensions = PersonalityDimension.values.map(dim => (dim,0)).toMap
  
  def apply(dim:PersonalityDimension):Int = {
    dimensions(dim)
  }
  
  def update(dim:PersonalityDimension,value:Int) = {
    import CharacterPersonality._
    dimensions = dimensions.updated(dim, math.min(MaxVal,math.max(MinVal,value)))
  }
  
  def randomize:Unit = {
    import CharacterPersonality._
    dimensions = PersonalityDimension.values.map(dim => 
      (dim,Random.rand.randInt(MaxVal - MinVal) - MinVal)
    ).toMap
  }
}

object CharacterPersonality {
  val MaxVal = 255
  val MinVal = 255
}