package game.entity.event


import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime
import game.entity.character.RelationshipType
import game.entity.event.trigger.EventTrigger
import game.entity.event.trigger.RelationshipTypeTrigger
import game.entity.event.trigger.MultiTrigger

trait RelationshipEvent extends Event {
  
  case class Other(val trigger:EventTrigger) extends EventTrigger {
    override def isTriggered(char:Character):Boolean = {
      trigger.isTriggered(char)
    } 
  }
  
  def relTrigger(trigger:EventTrigger, char1:Character, char2:Character):Boolean = {
    trigger match{
        case o:Other => {
          o.isTriggered(char2)
        }
        case r:RelationshipTypeTrigger => {
          !char1.relationships(char2,r.relType).isEmpty
        }
        case m:MultiTrigger => {
          m.seq.map(relTrigger(_,char1,char2)).foldLeft(m.requireAll){(v1, v2) =>
              if(m.requireAll){
            	  v1 && v2
              }
              else{
            	  v1 || v2
              }
          }
        }
        case _ => trigger.isTriggered(char1)
      }
  }
  
  def isTriggered(character:Character, character2:Character):Boolean = {
    val triggers = this.triggeredBy.filter{l =>
      relTrigger(l, character, character2)
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

