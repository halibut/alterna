package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.RelationshipType
import org.joda.time.DateTime
import game.entity.character.Character
import game.entity.gen.CharacterCreator
import game.entity.character.CharacterEventHelper

object BecomeFriends extends RelationshipEvent {
  
  Name = "Become Friends"

  TriggeredBy (
    ! RelationshipType.FriendsWith
  )
  
  Variables (
    "option" -> Choose("is dead","is no longer with us","died","will be mourned",
        "shuffled off this mortal coil","is at peace","is in a better place (maybe... no one really knows for sure)")
  )
  
  FlavorText (
    "<name> and <relationship-name> had a baby!"
  )

  
}
