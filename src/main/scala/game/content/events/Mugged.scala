package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object Mugged extends CharacterEvent {
  Name = "Mugged"

  TriggeredBy (
    YearlyChance(1,200),
    Stat.Strength-- Chance(1,50)
  )
  
  Variables (
    "adjective" -> Choose("petty","goddamn","crazy","sneaky","sly", "motherfucking"),
    "weapon" -> Choose("banana", "badass sword", "boomerang", "slingshot", "switchblade", "pair of nunchucks"),
    "activity" -> Choose("shopping", "smelling flowers", "frolicking through town", "strolling through town"),
    "insultingName" -> Choose("son of a bitch", "bastard", "asshole", "dickhead")
  )
  
  FlavorText (
    "<name> got mugged by some <adjective> thief wielding a <weapon>!",
	"While <name> was out <activity>, <subject-pronoun> was mugged by a <adjective> thief!"  
  )
  
  new Outcome {
    FlavorText(
		"So <name> murdered his ass.",
		"So <name> taught that <insultingName> an important lesson in dying."
	)
	DependsOn(
	    Stat.Strength++,
	    Personality.Brave++,
	    Personality.Pacifist---,
	    Personality.Good-
	)
	Result(
	    Stat.Strength+,
	    Personality.Brave+,
	    Personality.Pacifist-,
	    Personality.Good--
	)
  }
  
  new Outcome{
    FlavorText(
		"So <name> disarmed him and took him to the authorities."
	)
	DependsOn( 
		Stat.Strength++,
		Personality.Brave++,
		Personality.Pacifist+,
		Personality.Good+
	)
	Result(
		Stat.Strength+,
		Personality.Brave+,
		Personality.Pacifist+,
		Personality.Good++
	)
  }

  new Outcome{
    FlavorText(
		"<name> realized that thieves must make a killing doing this shit, so <subject-pronoun> decided to try it <reflexive-pronoun>."
	)
	DependsOn( 
		Stat.Focus+,
		Stat.Speed++,
		Personality.Generous---,
		Personality.Brave+,
		Personality.Good--
	)
	Result(
		Stat.Focus+,
		Stat.Strength+,
		Stat.Speed++,
		Stat.Evasion+,
		Personality.Generous---,
		Personality.Brave+,
		Personality.Good--
		//Ability.Steal(1,1)
	)
  }  

  new Outcome{
    FlavorText(
		"<name> had been on <possessive-pronoun> way to the bank to deposit a month's wages too. Damn."
	)
	DependsOn( 
		Stat.Luck---,
		Stat.Strength-,
		Stat.Focus-,
		Personality.Brave--,
		Personality.Pacifist++
	)
	Result(
		Personality.Brave-,
		Personality.Satisfied--
	)
  }  
  
    new Outcome{
    FlavorText(
		"<name> tried to chase him down, but couldn't catch up. <subject-pronoun> decided it was time to start exercising again."
	)
	DependsOn( 
		Stat.Speed---,
		Stat.Strength+,
		Stat.Focus+,
		Personality.Brave+,
		Personality.Hardworking++
	)
	Result(
		Stat.Strength+,
		Stat.Focus+,
		Stat.Speed++,
		Personality.Hardworking+
	)
  }
    
  new Outcome{
    FlavorText(
		"<name> very stupidly tried to fight him off... And was killed."
	)
	DependsOn(
		Stat.Strength---,
		Stat.Focus---,
		Personality.Pacifist---
	)
	Result(
		UnnaturalDeath
	)
  }
	
}

