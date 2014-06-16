package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object LightningStrike extends CharacterEvent {
  Name = "Lightning Strike"

  TriggeredBy (
    YearlyChance(1,500)
  )
  
    Variables (
  )
  
  FlavorText (
    "<name> was struck by lightning!",
	"While <name> was playing with <possessive-pronoun> set of giant metal rods one stormy day, <subject-pronoun> was struck by lightning!"  
  )
  
  new Outcome {
    FlavorText(
		"But <subject-pronoun> survived and actually felt strangely invigorated..."
	)
	DependsOn(
	    Stat.Spirit++,
	    Stat.Luck+++
	)
	Result(
	    Stat.Spirit++,
	    Personality.Brave+
	    //Talent.ShockEater(1/1)
	)
  }

  new Outcome {
    FlavorText(
        "But <subject-pronoun> survived it, no sweat!"
    )
    DependsOn(
        Stat.Spirit++,
        Stat.Luck+
    )
    Result(
        Stat.Spirit++,
        Personality.Brave+
        //Talent.Shockproof(1/1)
    )
}

new Outcome {
  FlavorText(
      "<subject-pronoun> survived, but just barely."
      )
  DependsOn(
      Stat.Spirit+
	)
  Result(
      Personality.Satisfied---,
      Personality.Brave--
      //Flaw.Astraphobe(1,4)
 )
}
	
new Outcome {
  FlavorText(
      "Or so it would seem. Thankfully, <name> managed to dodge it just in time!",
      "At least, <subject-pronoun> almost was. Luckily, <name> managed to scurry out of the way just in the nick of time!"
      )
  DependsOn(
      Stat.Evasion++,
      Stat.Speed+
      )
  Result(
      Stat.Evasion+++,
      Stat.Speed++,
      Personality.Brave++
    )	
}

  new Outcome{
    FlavorText(
		"And <subject-pronoun> was killed instantly.",
		"And <subject-pronoun> just didn't stand a chance.",
		"And <subject-pronoun> was toast."
	)
	DependsOn(
		Stat.Evasion--,
		Stat.Speed-,
		Stat.Spirit--
	)
	Result(
		UnnaturalDeath
	)
}
}