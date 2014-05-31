package game.entity.event.trigger

import game.entity.character.Character
import game.entity.event.likelihood.Likelihood
import game.random.Random

case class ChanceTrigger(probability:Double) extends EventTrigger {
  
   def isTriggered(character:Character):Boolean = {
     Random.rand.randDouble <= probability 
   }
  
}

