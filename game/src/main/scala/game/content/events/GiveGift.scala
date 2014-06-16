package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object GiveGift extends CharacterEvent {
  Name = "Give Gift"

  TriggeredBy (
    Personality.Generous+ Chance(1,20)
  )
 
  Variables (
    "adjective" -> Choose("special","handmade","fantastic","personally crafted","kickass"),
    "gift" -> Choose("gift","present","surprise","helping hand"),
    "good-feeling" -> Choose("grateful","happy","pleased"),
    "reward-action" -> Choose("rewarded <object-pronoun> in kind","gave <object-pronoun> a finely aged American cheese",
        "gave <object-pronoun> a kiss on the mouth","sent <object-pronoun> a hand written thank you card",
        "died and left a sizeable inheritance","invited <object-pronoun> to the town's hottest party"),
    "bad-adjective" -> Choose("lame","tacky","cheap","gaudy","pretentious","hipster","chintzy","dumb","stupid","flimsy",
        "underwhelming"),
    "rejected-action" -> Choose("threw it on the ground","stomped all over it","lit it on fire","threw it off a cliff",
        "chucked it","peed all over it","whiped his ass with it","used it for target practice","returned it",
        "asked for a receipt","walked away sad like Charlie Brown","nuked it from orbit")
  )

    FlavorText (
    "<name> came upon a stranger who said he needed help. <name> was feeling generous and gave him a <adjective> <gift>."  
  )
  
   
  new Outcome {
    FlavorText(
		"The stranger was incredibly <good-feeling> for <name>'s generosity and later <reward-action>."
	)
	DependsOn(
	    Personality.Charismatic+,
	    Personality.Friendly++,
	    Stat.Luck+
	)
	Result(
	    Personality.Generous+++,
	    Personality.Satisfied++,
	    Personality.Friendly+
	)
  }

  new Outcome{
    FlavorText(
		"But the stranger thought the gift was too <bad-adjective>, and <rejected-action>!"
	)
	DependsOn( 
		Personality.Friendly-,
		Personality.Charismatic--
	)
	Result(
		Personality.Charismatic-,
		Personality.Friendly--,
		Personality.Generous---
	)
  }
  
  
}