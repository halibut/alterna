package game.entity.event

import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime

trait CharacterEvent extends Event {
  
  def isTriggered(character:Character):Boolean = {
    val triggers = this.triggeredBy.filter{l =>
      l.isTriggered(character)
    } 
    
    !triggers.isEmpty
  }
  
  def resolve(character:Character, date:Option[DateTime] = None):LifeEvent = {
    val vars = EventVariables.getEventVars(variables, character) 
    
    val triggerFlavorText = getFlavorText(vars)
    
    val attunements = outcomes.map{outcome =>
      outcome -> outcome.calcAttunement(character)
    }
    
    val eventDate = date.getOrElse(character.world.get.year) 
    
    val outcome = Bag(attunements:_*).get.get
    val outcomeFlavorText = outcome.getFlavorText(vars)
    outcome.resolve(character, eventDate)
  
    LifeEvent(LifeEventType.PhysicalTrial,
        eventDate,
        character,
        triggerFlavorText + " " + outcomeFlavorText
        )
    
  }
}

