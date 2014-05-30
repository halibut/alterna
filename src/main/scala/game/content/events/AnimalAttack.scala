package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.event.likelihood.StatBasedLikelihood._
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}
import game.entity.event.likelihood.StatBasedLikelihood

object AnimalAttack extends CharacterEvent {
  Name = "Animal Attack"

  Likelihood (
    YearlyChance(1,20)
  )
  
  Variables (
    "animal" -> Choose("a bear","a cat","a giraffe","an ocelot","a swarm of bees","a wolf","a moose")
  )
  
  FlavorText (
	"${name} was attacked by ${animal}!",
	"While ${name} was sleeping, ${animal} smashed through the window and tried to eat ${object-pronoun}!"  
  )
  
  new Outcome {
    
    FlavorText(
		"But ${subject-pronoun} fought it off!",
		"But ${subject-pronoun} escaped with ${possessive-pronoun} life!"
	)
	
	DependsOn(
	    Stat.Strength++,
	    Stat.Defense+,
	    Stat.Evasion+,
	    Personality.Brave+,
	    Personality.Pacifist-
	)
	
//	DependsOn:{ 
//		++Stat.Strength
//		+Stat.Defense
//		+Stat.Evasion
//		+++Stat.Luck
//		+Personality.Brave
//		-Personality.Pacifist
//	}
	
	
	
	Result(
	    Stat.Strength++,
	    Stat.Evasion+,
	    Stat.Luck+,
	    Personality.Brave+,
	    Personality.Pacifist-
	)
	
//	Result:{
//		++Stat.Strength
//		+Stat.Evasion
//		+Stat.Luck
//		+Personality.Brave
//		-Personality.Pacifist
//		Talent.AnimalAfinity(1/5)
//	}
  }
  
  new Outcome{
    
  }
	
	
}
  




/*
Outcome 2:{
	
	Flavor Text:{
		And ${subject-pronoun} was maimed.
		And it seriously wounded ${object-pronoun}.
	}
	Depends On:{ 
		--Stat.Strength
		--Stat.Luck
		+++Personality.Pacifist
	}
	Result:{
		-Stat.Strength
		-Stat.Evasion
		--Stat.Defense
		-Personality.Brave
		-Personality.Charismatic
		+Personality.Pacifist
		Talent.AnimalAfinity(1/5)
	}
}

Outcome 3:{
	Flavor Text:{
		And ${subject-pronoun}... lost ${posessive-pronoun} life.
		But ${subject-pronoun} was overpowered and died.
	}
	Depends On:{
		---Stat.Strength
		---Stat.Luck
		+++Personality.Pacifist
	}
	Result:{
		Event.Death
	}
}
*/