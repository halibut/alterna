package game.entity.character

import game.entity.event.LifeEvent

trait CharacterRelationship {

  val startEvent:LifeEvent;
  var endEvent:Option[LifeEvent];
  val from:Character;
  val to:Character;
  var strength:Int;
  var relationshipType:RelationshipType
}