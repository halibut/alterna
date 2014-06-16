package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.RelationshipType
import org.joda.time.DateTime
import game.entity.character.Character
import game.entity.gen.CharacterCreator
import game.entity.character.CharacterEventHelper
import game.entity.character.Personality

object BecomeFriends extends RelationshipEvent {
  
  Name = "Become Friends"
    
  NotTriggeredIf (
     RelationshipType.FriendsWith,
     RelationshipType.ChildOf,
     RelationshipType.MarriedTo,
     RelationshipType.ParentOf,
     RelationshipType.SiblingsWith,
     Age(0,5),
     Other(Age(0,5))
  )

  TriggeredBy (
    YearlyChance(1,4) && PersonalityAlign(.25)
  )
  
  Variables (
  )
  
  FlavorText (
    ""
  )

  new Outcome{
    FlavorText (
      "<name> and <relationship-name> are now friends!"
    )
    
    Result (
      Personality.Friendly+,
      AddRelationship(RelationshipType.FriendsWith),
      BecomeFriends -> OtherCharacter
    )
  }
  
}
