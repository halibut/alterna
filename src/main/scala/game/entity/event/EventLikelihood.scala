package game.entity.event

import game.random.Random
import game.entity.character.Character
import game.entity.character.StatType
import game.entity.character.CharacterStats
import game.entity.character.PersonalityDimension
import game.entity.character.CharacterPersonality

trait EventLikelihood {
  def chance(character: Character): Double
}

class YearlyChanceLikelihood(val chances: Int, val outOf: Int) extends EventLikelihood {
  def chance(character: Character): Double = {
    chances.toDouble / outOf.toDouble
  }
}

class StatbasedLikelihood(val stat: StatType, weight: StatWeight) extends EventLikelihood {
  def chance(character: Character): Double = {
    val stats = character.stats
    val halfLevel = stats.avgLevel.toDouble * 0.5
    val amount = stats(stat).toDouble * weight.getMultiplier() * 0.5 + halfLevel
    math.min(1.0, math.max(0.0, amount))
  }
}

class PersonalitybasedLikelihood(val pDim: PersonalityDimension, weight: StatWeight) extends EventLikelihood {
  def chance(character: Character): Double = {
    import CharacterPersonality._
    val personality = character.personality
    val dimAmount = personality(pDim).toDouble * weight.getMultiplier()
    val amount = if (dimAmount < 0) -dimAmount / MinVal else dimAmount / MaxVal
    math.min(1.0, math.max(0.0, amount))
  }
}

class MultipleLikelihood(var likelihoodMethods: Seq[EventLikelihood]) extends EventLikelihood {

  def chance(character: Character): Double = {
    val total = likelihoodMethods.map(_.chance(character)).sum.toDouble /
      likelihoodMethods.size.toDouble
    total
  }
}

object EventLikelihood {
  def apply(strRep:String):EventLikelihood = {
    val lines = strRep.split("\n").map(_.trim())
    		.filterNot(_.isEmpty()).map(_.toLowerCase())
    		
    val likelihoodSeq:Array[EventLikelihood] = lines.map{line =>
      if(line.contains("stat.")){
        val parts = line.split("stat.")
        val statType = StatType.valueOf(parts(1).capitalize)
        val statWeight = StatWeight.getWeightFromSymbol(parts(0))
        new StatbasedLikelihood(statType, statWeight)
      }
      else if(line.contains("personality.")){
        val parts = line.split("personality.")
        val personalityDim = PersonalityDimension.valueOf(parts(1).capitalize)
        val statWeight = StatWeight.getWeightFromSymbol(parts(0))
        new PersonalitybasedLikelihood(personalityDim, statWeight)
      }
      else if(line.contains("yearlychance")){
        val numbers = line.replaceAll("[^\\d/]","")
        val parts = numbers.split("/")
        new YearlyChanceLikelihood(parts(0).toInt, parts(1).toInt)
      }
      else{
        throw new IllegalArgumentException("Could not parse likelihood from line: "+line)
      }
    }
    
    new MultipleLikelihood(likelihoodSeq)
  }
}