package game.entity.event

import game.entity.character.RelationshipType
import game.entity.event.outcome.Affects
import game.entity.event.outcome.CharacterRelations
import game.entity.event.outcome.SelfRelation
import game.entity.character.Character

trait TriggerableEvent {
  self:Event =>
 
  override def isTriggered(character:Character):Boolean = {
    false
  }
}

