package game.entity.event.outcome

import game.entity.event.Event
import game.entity.character.Character
import game.entity.event.RelationshipEvent
import game.entity.event.CharacterEvent
import org.joda.time.DateTime

class TriggerCharacterEventResult(val event:CharacterEvent) extends Result {
  
  def updateCharacter(character:Character, date:DateTime):Unit ={
    event.resolve(character, Some(date))
  }
}