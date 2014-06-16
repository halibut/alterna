package game.content.events

import game.entity.event._
import game.entity.event.outcome._
import game.entity.character.Stat
import game.entity.character.Personality
import scala.language.{implicitConversions,postfixOps}


object TreeStrike extends CharacterEvent {
  Name = "Tree Strike"

  TriggeredBy (
    YearlyChance(1,500)
  )
  
    Variables (
        "adjective" -> Choose("a rogue","an antagonistic","a goddamn","a freakin'","a devious", "a dastardly")
  )
  
  FlavorText (
    "One day, as <name> strolled through the forest, <adjective> tree decided to flail its branches at <object-pronoun>!",
	"While <name> was in the woods, a tree suddenly rose out of the ground right beneath <object-pronoun>!"  
  )
  
  new Outcome {
    FlavorText(
		"But <subject-pronoun> survived and discovered that <subject-pronoun> REALLY had a thing for trees..."
	)
	DependsOn(
	    Stat.Spirit++,
	    Stat.Luck+++
	)
	Result(
	    Stat.Spirit++,
	    Personality.Brave+
	    //Talent.TreeEater(1/1)
	)
  }

  new Outcome {
    FlavorText(
        "But <subject-pronoun> survived it! In fact, no tree could lay a branch on <object-pronoun>!"
    )
    DependsOn(
        Stat.Spirit++,
        Stat.Luck+
    )
    Result(
        Stat.Spirit++,
        Personality.Brave+
        //Talent.Treeproof(1/1)
    )
}

new Outcome {
  FlavorText(
      "<subject-pronoun> survived, but not without a painful branch-whippin'."
      )
  DependsOn(
      Stat.Spirit+
	)
  Result(
      Personality.Satisfied---,
      Personality.Brave--
      //Flaw.Dendrophobe(1,4)
 )
}
	
new Outcome {
  FlavorText(
      "Good thing <name> managed to dodge it!",
      "Reacting quickly, <subject-pronoun> leapt out of the way!"
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
		"And <subject-pronoun> was branch whipped to death.",
		"It filled <object-pronoun> up with twigs and bark until <subject-pronoun> died."
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