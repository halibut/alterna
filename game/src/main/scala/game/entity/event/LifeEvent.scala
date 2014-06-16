package game.entity.event

import org.joda.time.DateTime
import game.entity.character.Character
import org.joda.time.format.DateTimeFormat
import game.tools.DateUtils._


case class LifeEvent(
	val eventType:LifeEventType,
    val date:DateTime,
    val character:Character,
    val description:String
){
  override def toString():String = {
    date.fmtDate("MM-dd-yyyy") + " - " + description 
  }
}
