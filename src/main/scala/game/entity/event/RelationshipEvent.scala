package game.entity.event


import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime
import game.entity.character.RelationshipType
import game.entity.event.trigger.EventTrigger
import game.entity.event.trigger.RelationshipBasedTrigger
import game.entity.event.trigger.MultiTrigger
import game.entity.event.trigger.NotTrigger
import game.entity.event.trigger.TriggerData
import game.entity.event.trigger.TwoCharTriggerData
import game.entity.event.trigger.OneCharTriggerData
import game.entity.event.trigger.TwoCharTriggerData

trait RelationshipEvent extends Event {
  
  case class Other(val trigger:EventTrigger) extends EventTrigger {
    override def isTriggered(td:TriggerData):Boolean = {
      td match {
        case tcd:TwoCharTriggerData => trigger.isTriggered(new OneCharTriggerData(tcd.char1))
        case _ => false
      }
    } 
  }
  
  def isTriggered(character:Character, character2:Character):Boolean = {
    val td = new TwoCharTriggerData(character, character2) 
      
    val triggers = this.triggeredBy.filter{l =>
      l.isTriggered(td)
    } 
    
    !triggers.isEmpty
  }
  
  def resolve(toChar:Character, fromChar:Character, date:Option[DateTime] = None):Option[LifeEvent] = {
    val relType = toChar.relationships(fromChar).headOption
    		.map(_.relationshipType.getObjectOfRelationship())
    		.getOrElse("who-the-hell-knows-what-they-were")
    
    Variables(
        "relationship-type" -> new StringVariable(relType),
        "relationship-name" -> new StringVariable(fromChar.firstName)
    )		
    
    val vars = EventVariables.getEventVars(variables, toChar) 
    
    val triggerFlavorText = getFlavorText(vars)
    
    val attunements = outcomes.map{outcome =>
      outcome -> outcome.calcAttunement(toChar)
    }
    
    val eventDate = date.getOrElse(toChar.world.get.year).hourOfDay().addToCopy(1)
    
    val outcome = Bag(attunements:_*).get.get
    val outcomeFlavorText = outcome.getFlavorText(vars)
    outcome.resolve(toChar,eventDate)
  
    Some(LifeEvent(LifeEventType.RelationshipChange,
        eventDate,
        toChar,
        triggerFlavorText + " " + outcomeFlavorText
        ))
  }
}

