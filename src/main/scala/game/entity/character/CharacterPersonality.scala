package game.entity.character

import game.random.Random

class CharacterPersonality {

  private var dimensions = Personality.values.map(dim => (dim,0)).toMap
  
  def apply(dim:Personality):Int = {
    dimensions(dim)
  }
  
  def update(dim:Personality,value:Int) = {
    import CharacterPersonality._
    dimensions = dimensions.updated(dim, math.min(MaxVal,math.max(MinVal,value)))
  }
  
  def randomize:Unit = {
    import CharacterPersonality._
    dimensions = Personality.values.map(dim =>  
      (dim,Random.rand.randInt(MaxVal - MinVal) - MinVal)
    ).toMap
  }
}

object CharacterPersonality {
  val MaxVal = 255
  val MinVal = -255
}