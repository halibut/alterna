package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object BadHaircut extends CharacterEvent {
  Name = "Bad Haircut"

  TriggeredBy (
    YearlyChance(1,5),
    Stat.Luck-- Chance(1,3)
  )
 
  Variables (
    "adjective" -> Choose("terrible","horrible","bad","ridiculous","really fucking dumb")
  )

    FlavorText (
    "<name> went to the local salon and got a <adjective> haircut."  
  )
  
   
  new Outcome {
    FlavorText(
		"But <subject-pronoun> totally rocked it anyway!"
	)
	DependsOn(
	    Personality.Charismatic+++
	)
	Result(
	    Personality.Brave+,
	    Personality.Charismatic++,
	    Stat.Spirit+
	)
  }

  new Outcome{
    FlavorText(
		"And <subject-pronoun> was the laughingstock of the whole town for a couple of months.",
		"And it was super fucking embarrassing.",
		"And <name> wouldn't even leave <possessive-pronoun> room for an entire month."
	)
	DependsOn( 
		Personality.Charismatic-
	)
	Result(
		Personality.Charismatic--,
		Stat.Spirit-,
		Stat.Evasion+
	)
  }
  
  
}