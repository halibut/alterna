package game.entity.event.outcome

import game.entity.event.Event
import game.entity.character.Character
import game.entity.event.RelationshipEvent
import org.joda.time.DateTime
import game.entity.event.LifeEventType

class TriggerRelationshipEventResult(val eventAndRelations: (RelationshipEvent, Affects)) extends Result {
  val (event, affects) = eventAndRelations

  def updateCharacter(character: Character, date: DateTime): Unit = {

    for (
      toChar <- affects.affected(character).distinct;
      if (!toChar.isDeceased)
    ) {

      val ev = event.resolve(toChar, character, Some(date))

      ev.foreach { e =>
        e.eventType match {
          case LifeEventType.EndRelationship => {
            toChar.relationships.remove(character)
          }
          case _ => {}
        }
        toChar.lifeEvents.add(e)
      }

    }
  }
}