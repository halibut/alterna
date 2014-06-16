package game.content.events

import game.entity.event._
import game.entity.event.outcome._

object FriendDiedUnnaturally extends RelationshipEvent {
  
  Name = "Friend Died Unnaturally"

  TriggeredBy (
    AnotherEvent
  )
  
  Variables (
    "option" -> Choose("is dead","is no longer with us","died","will be mourned",
        "shuffled off this mortal coil","is at peace","is in a better place (maybe... no one really knows for sure)")
  )
  
  FlavorText (
    "<name>'s <relationship-type>, <relationship-name>, <option>."
  )
  
  new Outcome {
    FlavorText("")
	
    DependsOn(
        
    )
    
	Result(
	    
	)
  }
}
