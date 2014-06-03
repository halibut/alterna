package game.entity.event.trigger

import game.entity.character.RelationshipType
import game.entity.character.Character

class AgeTrigger(val start:Int, val end:Int) extends EventTrigger {

  def isTriggered(character:Character):Boolean = {
    character.age >= start && character.age <= end
  }
}