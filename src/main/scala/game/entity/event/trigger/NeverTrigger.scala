package game.entity.event.trigger

import game.entity.character.Character
import game.entity.event.likelihood.Likelihood
import game.random.Random

object NeverTrigger extends EventTrigger {
  
   def isTriggered(character:Character):Boolean = {
     false 
   }
  
}

