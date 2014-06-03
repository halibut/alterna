package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.RelationshipType
import org.joda.time.DateTime
import game.entity.character.Character
import game.entity.gen.CharacterCreator
import game.entity.character.CharacterEventHelper

object Birth extends RelationshipEvent {
  
  Name = "Birth"

  TriggeredBy (
    Age(18,50) and Other(Age(18,50)) and RelationshipType.MarriedTo Chance(1,10)
  )
  
  Variables (
    "option" -> Choose("is dead","is no longer with us","died","will be mourned",
        "shuffled off this mortal coil","is at peace","is in a better place (maybe... no one really knows for sure)")
  )
  
  FlavorText (
    "<name> and <relationship-name> had a baby!"
  )
  
  
  override def resolve(toChar:Character, fromChar:Character, date:Option[DateTime] = None):Option[LifeEvent] = {
    Variables(
        "relationship-name" -> new StringVariable(fromChar.firstName)
    )		
    
    val vars = EventVariables.getEventVars(variables, toChar) 
    
    val triggerFlavorText = getFlavorText(vars)
    
    val eventDate = date.getOrElse(toChar.world.get.year).hourOfDay().addToCopy(1)
    
    val baby = CharacterCreator.initCharacter
    baby.birthDate = Some(eventDate)
    toChar.world.get.addCharacter(baby)
    
    CharacterEventHelper.birth(toChar.headOfFamily, baby)
    
    None
  }
  
}
