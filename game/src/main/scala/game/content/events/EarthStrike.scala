package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object EarthStrike extends CharacterEvent {
  Name = "Earth Strike"

  TriggeredBy (
    YearlyChance(1,500)
  )
  
    Variables (
        "activity" -> Choose("training","walking","wandering","practicing <possessive-pronoun> sweet dance moves", "farting", "masturbating"),
        "geo" -> Choose("gorge","valley","pit"),
        "group" -> Choose("a gang of rodents", "a pack of howling monkeys", "a local gang"),
        "adjective" -> Choose("goddamn", "caked", "fucking", "dang")
  )
  
  FlavorText (
    "As <name> was <activity> in the nearby <geo>, <subject-pronoun> looked up and saw an incoming mudslide!",
	"<name> pissed off <group> by <activity> on their turf! They proceeded to launch a massive mudpie attack."  
  )
  
  new Outcome {
    FlavorText(
		"But <name> realized it was the most delicious thing <subject-pronoun> had ever tasted and ate <possessive-pronoun> way to victory!"
	)
	DependsOn(
	    Stat.Spirit++,
	    Stat.Luck+++
	)
	Result(
	    Stat.Spirit++,
	    Personality.Brave+
	    //Talent.EarthEater(1/1)
	)
  }

  new Outcome {
    FlavorText(
        "But <name> survived the onslaught and continued what <subject-pronoun> was doing before, completely unfazed."
    )
    DependsOn(
        Stat.Spirit++,
        Stat.Luck+
    )
    Result(
        Stat.Spirit++,
        Personality.Brave+
        //Talent.Earthproof(1/1)
    )
}

new Outcome {
  FlavorText(
      "<subject-pronoun> survived, but it took weeks to clean all the <adjective> mud out of <possessive-pronoun> ears."
      )
  DependsOn(
      Stat.Spirit+
	)
  Result(
      Personality.Satisfied---,
      Personality.Brave--
      //Flaw.Mysophobe(1,4)
 )
}
	
new Outcome {
  FlavorText(
      "But <name> sidestepped it with ease."
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
		"<subject-pronoun> was killed instantly.",
		"And <subject-pronoun> could not withstand it and died."
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