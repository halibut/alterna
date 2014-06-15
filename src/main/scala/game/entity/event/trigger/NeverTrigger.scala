package game.entity.event.trigger

import game.entity.character.Character
import game.entity.event.likelihood.Likelihood
import game.random.Random

object NeverTrigger extends EventTrigger {
  
   def isTriggered(triggerData:TriggerData):Boolean = {
     false 
   }
  
}

