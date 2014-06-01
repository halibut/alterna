package game.entity.event


import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime

trait RelationshipEvent extends Event {
  
  def resolve(toChar:Character, fromChar:Character, date:Option[DateTime] = None):LifeEvent = {
    val vars = EventVariables.getEventVars(variables, toChar) 
    
    val triggerFlavorText = flavorText.get.getOrElse("").replaceVars(vars,"<",">").capitalize
    
    val attunements = outcomes.map{outcome =>
      outcome -> outcome.calcAttunement(toChar)
    }
    
    val eventDate = date.getOrElse(toChar.world.get.year).hourOfDay().addToCopy(1)
    
    val outcome = Bag(attunements:_*).get.get
    val outcomeFlavorText = outcome.flavorText.get.getOrElse("").replaceVars(vars,"<",">").capitalize
    outcome.resolve(toChar,eventDate)
  
    LifeEvent(LifeEventType.RelationshipChange,
        eventDate,
        toChar,
        triggerFlavorText + " " + outcomeFlavorText
        )
  }
}

