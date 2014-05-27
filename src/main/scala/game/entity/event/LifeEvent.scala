package game.entity.event

import org.joda.time.DateTime
import game.entity.character.Character
import org.joda.time.format.DateTimeFormat


case class LifeEvent(
	val eventType:LifeEventType,
    val date:DateTime,
    val character:Character,
    val description:String
){
  override def toString():String = {
    DateTimeFormat.forPattern("MM-dd-yyyy").print(date) + " - " + description 
  }
}
