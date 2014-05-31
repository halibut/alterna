package game.entity.event.trigger

import game.entity.character.Character

trait EventTrigger {
	
  def isTriggered(character:Character):Boolean
  
}