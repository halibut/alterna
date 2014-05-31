package game.entity.event.trigger

import game.entity.character.Stat
import game.entity.event.StatWeight
import game.entity.character.Character
import game.random.Random

class StatBasedTrigger(statAndWeight:(Stat,StatWeight), probability:Double) extends EventTrigger{
  val (stat, weight) = statAndWeight

  def isTriggered(character:Character):Boolean = {
    val s = character.stats(stat).toDouble / character.level.toDouble
    val req = weight match{
      case StatWeight.SlightPositive => s > 1
      case StatWeight.ModeratePositive => s > 2
      case StatWeight.HeavyPositive => s > 4
      case StatWeight.SlightNegative => s < 1
      case StatWeight.ModerateNegative => s < 0.5
      case StatWeight.HeavyNegative => s < 0.25
    }
    
    req && Random.rand.randDouble <= probability 
  }
  
}