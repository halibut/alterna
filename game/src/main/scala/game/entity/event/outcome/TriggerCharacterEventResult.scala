package game.entity.event.outcome

import game.entity.event.Event
import game.entity.character.Character
import game.entity.event.RelationshipEvent
import game.entity.event.CharacterEvent
import org.joda.time.DateTime
import game.entity.event.LifeEventType

class TriggerCharacterEventResult(val event:CharacterEvent) extends Result {
  
  def updateCharacter(character:Character, date:DateTime):Unit ={
    val e = event.resolve(character, Some(date))
    
    e.eventType match {
      case LifeEventType.Death => {
        character.deathDate = Some(e.date)
      }
      case _ => {}
    }
    
    character.lifeEvents.add(e)
  }
}