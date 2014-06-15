package game.entity.event.trigger

import game.entity.character.RelationshipType
import game.entity.character.Character

class RelationshipTypeTrigger(val relType:RelationshipType) extends EventTrigger {

  def isTriggered(character:Character):Boolean = {
    val rels = character.relationships(relType)
    !rels.isEmpty
  }
}