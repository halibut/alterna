package game.entity.event.trigger

import game.entity.character.Stat
import game.entity.event.StatWeight
import game.entity.character.Character
import game.random.Random
import game.entity.character.Personality

class PersonalityBasedTrigger(persAndWeight:(Personality,StatWeight), probability:Double) extends EventTrigger{
  val (pers, weight) = persAndWeight

  override def isTriggered(td:TriggerData):Boolean = {
    td match {
      case ocd:OneCharTriggerData => isTriggered(ocd.character)
      case _ => false
    }
  }
  
  def isTriggered(character:Character):Boolean = {
    val s = (character.personality(pers) - pers.getMin()).toDouble / pers.getRange().toDouble
    val req = weight match{
      case StatWeight.SlightPositive => s > .5
      case StatWeight.ModeratePositive => s > .7
      case StatWeight.HeavyPositive => s > .85
      case StatWeight.SlightNegative => s < .5
      case StatWeight.ModerateNegative => s < .3
      case StatWeight.HeavyNegative => s < 0.15
    }
    
    req && Random.rand.randDouble <= probability 
  }
  
}