package game.entity.character

import game.entity.event.LifeEvent

case class Relationship(
  val startEvent:LifeEvent,
  val from:Character,
  val to:Character,
  var relationshipType:RelationshipType,
  var strength:Int,
  var endEvent:Option[LifeEvent] = None
)