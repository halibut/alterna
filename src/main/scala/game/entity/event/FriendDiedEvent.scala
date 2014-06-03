package game.entity.event


import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime

trait FriendDiedEvent extends RelationshipEvent {
  
  override def resolve(toChar:Character, fromChar:Character, date:Option[DateTime] = None):Option[LifeEvent] = {
    val origEvent = resolve(toChar, fromChar, date)
    
   
    origEvent
  }
}

