package game.entity.event.outcome

import game.entity.event.Event
import game.entity.character.Character
import game.entity.event.RelationshipEvent
import org.joda.time.DateTime

class TriggerRelationshipEventResult(val eventAndRelations:(RelationshipEvent,Affects)) extends Result {
  val (event,affects) = eventAndRelations 
  
  def updateCharacter(character:Character, date:DateTime):Unit ={
    
    for(toChar <- affects.affected(character).distinct){
      event.resolve(toChar, character)
    }
  }
}