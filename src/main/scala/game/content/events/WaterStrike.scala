package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object WaterStrike extends CharacterEvent {
  Name = "Water Strike"

  TriggeredBy (
    YearlyChance(1,200)
  )
  
    Variables (
        "bodyPart" -> Choose("feet","face","hands","elbows","fingers","hair"),
        "activity" -> Choose("fishing","soaking <possessive-pronoun> <bodyPart>","having sex"),
        "bodyOfWater" -> Choose("lake","stream","river","pond","lagoon","swamp","puddle"),
        "modifier" -> Choose("extremely","incredibly","unbelievably")
  )
  
  FlavorText (
    "Suddenly, while <name> was <activity> in the nearby <bodyOfWater>, a tidal wave threatened to drown <object-pronoun>!"
  )
  
  new Outcome {
    FlavorText(
		"But <name> was feeling <modifier> thirsty anyway, so <subject-pronoun> just drank it away!"
	)
	DependsOn(
	    Stat.Spirit++,
	    Stat.Luck+++
	)
	Result(
	    Stat.Spirit++,
	    Personality.Brave+
	    //Talent.WaterEater(1/1)
	)
  }

  new Outcome {
    FlavorText(
        "But <name> just let it wash over <object-pronoun> and went back to <activity>."
    )
    DependsOn(
        Stat.Spirit++,
        Stat.Luck+
    )
    Result(
        Stat.Spirit++,
        Personality.Brave+
        //Talent.Waterproof(1/1)
    )
}

new Outcome {
  FlavorText(
      "<subject-pronoun> survived, but was plagued with dreams of drowning for several weeks."
      )
  DependsOn(
      Stat.Spirit+
	)
  Result(
      Personality.Satisfied---,
      Personality.Brave--
      //Flaw.Aquaphobe(1,4)
 )
}
	
new Outcome {
  FlavorText(
      "But <subject-pronoun> swiftly got out of the way!"
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
		"And <name>'s last thought was that <subject-pronoun> sure wished <subject-pronoun> had taken swimming lessons."
	)
	DependsOn(
		Stat.Evasion--,
		Stat.Speed-,
		Stat.Spirit--
	)
	Result(
		//Event.Death
	)
}
}