package game.entity

import game.random.Random

trait PersonalityDimension{
  val name:String
  PersonalityDimension.values = PersonalityDimension.values ::: List(this)
}

object PersonalityDimension{
  var values:List[PersonalityDimension] = List()
  val maxVal = 255
  
  case object Outgoing extends PersonalityDimension{val name="Outgoing"}
  case object Pacifist extends PersonalityDimension{val name="Pacifist"}
  case object Friendly extends PersonalityDimension{val name="Friendly"}
  case object Good extends PersonalityDimension{val name="Good"}
  case object Hardworking extends PersonalityDimension{val name="Hardworking"}
  case object Brave extends PersonalityDimension{val name="Brave"}
  case object Generous extends PersonalityDimension{val name="Generous"}
}

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
      (dim,Random.rand.randInt(PersonalityDimension.maxVal * 2 + 1) - PersonalityDimension.maxVal -1)
    ).toMap
  }
    
}