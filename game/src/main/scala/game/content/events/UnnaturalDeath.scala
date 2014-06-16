package game.content.events

import game.entity.event._
import game.entity.event.outcome._

object UnnaturalDeath extends CharacterDeathEvent {
  
  Name = "Unnatural Death"
  
  Variables (
    "option" -> Choose("is dead","is no longer with us","died","will be mourned","lost <possessive-pronoun> life",
        "shuffled off this mortal coil","is at peace","is in a better place (maybe... no one really knows for sure)",
        "met <possessive-pronoun> maker")
  )
  
  FlavorText (
    "<name>... <option>. RIP"
  )
  
  new Outcome {
    FlavorText("")
	
	Result(
	    FriendDiedUnnaturally -> AllRelations
	)
  }
}
