package game.entity.character

import game.random.Random

class CharacterPersonality {

  private var dimensions = PersonalityDimension.values.map(dim => (dim,0)).toMap
  
  def apply(dim:PersonalityDimension):Int = {
    dimensions(dim)
  }
  
  def update(dim:PersonalityDimension,value:Int) = {
    dimensions = dimensions.updated(dim, value)
  }
  
  def randomize:Unit = {
    dimensions = PersonalityDimension.values.map(dim => 
      (dim,Random.rand.randInt(CharacterPersonality.MaxVal * 2 + 1) - CharacterPersonality.MaxVal -1)
    ).toMap
  }
}

object CharacterPersonality {
  val MaxVal = 255
}