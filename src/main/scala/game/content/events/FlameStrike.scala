package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object FlameStrike extends CharacterEvent {
  Name = "Flame Strike"

  TriggeredBy (
    YearlyChance(1,100)
  )
  
    Variables (
    "animal" -> Choose("lizard","snake","maggot","rhino","pigeon","cat","butterfly"),
    "bodyPart" -> Choose("chest","arm","leg","ankle","wrist","head","neck","foot","back","ass")
  )
  
  FlavorText (
    "One day, while <name> was out and about, minding <possessive-pronoun> own business, a fire<animal> launched a flame ball right at <possessive-pronoun> <bodyPart> for no fucking reason whatsoever! (Dick.)"
  )
  
  new Outcome {
    FlavorText(
		"But <subject-pronoun> survived. Heck, more than survived--<name> actually felt all the better for it!"
        )
	DependsOn(
		Stat.Spirit++,
		Stat.Luck+++
	)
	Result(
		Stat.Spirit++,
		Personality.Brave+
		//Talent.FlameEater(1,1)
	)
}

new Outcome{
    FlavorText(
		"But <subject-pronoun> survived it, no sweat! Didn't even hurt."
	)
	DependsOn(
		Stat.Spirit++,
		Stat.Luck+
	)
	Result(
	    Stat.Spirit++,
	    Personality.Brave+
		//Talent.Flameproof(1,1)
	)
  }
	

new Outcome{
    FlavorText(
		"<subject-pronoun> survived, but only just."
	)
	DependsOn(
		Stat.Spirit+
	)
	Result(
	    Stat.Spirit+,
	    Personality.Satisfied--,
	    Personality.Brave--
	    //Flaw.Flamephobe(1,4)
	)
  }

new Outcome{
    FlavorText(
		"Thankfully, <name> managed to dodge it before <subject-pronoun> got hurt!"
	)
	DependsOn(
		Stat.Evasion++,
		Stat.Speed+
	)
	Result(
	    Stat.Evasion++,
	    Stat.Speed++,
	    Personality.Brave+
	)
  }

new Outcome{
    FlavorText(
		"And <subject-pronoun> was killed instantly.",
		"And <subject-pronoun> just didn't stand a chance.",
		"And <subject-pronoun> was roasted alive."
	)
	DependsOn(
		Stat.Evasion--,
		Stat.Spirit--,
		Stat.Speed-
	)
	Result(
	    //Event.Death
	)
  }
}