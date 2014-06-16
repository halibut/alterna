package game.entity.event.trigger

import game.entity.character.Character

class NotTrigger(val trigger:EventTrigger) extends EventTrigger {

  def isTriggered(td:TriggerData):Boolean = {
    !trigger.isTriggered(td)
  }
  
}