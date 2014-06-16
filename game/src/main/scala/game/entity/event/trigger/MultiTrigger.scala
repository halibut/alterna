package game.entity.event.trigger

import game.entity.character.Character

class MultiTrigger(val seq:Seq[EventTrigger], val requireAll:Boolean) extends EventTrigger {
  
  def isTriggered(td: TriggerData): Boolean = {
    val triggerResults = seq.map(_.isTriggered(td))
    triggerResults.foldLeft(requireAll){case (v1,v2) =>
      if(requireAll){
        v1 && v2
      }
      else{
        v1 || v2
      }
    }
  }

}