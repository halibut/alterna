package game.entity.event.likelihood

import game.entity.character.Stat
import game.entity.character.Character
import game.entity.event.StatWeight

class StatBasedLikelihood(statAndWeight:(Stat,StatWeight)) extends Likelihood {
  val (stat,weight) = statAndWeight
  
  def chance(character: Character): Double = {
    val stats = character.stats
    val halfLevel = stats.avgLevel.toDouble * 0.5
    val amount = stats(stat).toDouble * weight.getMultiplier() * 0.5 + halfLevel
    math.min(1.0, math.max(0.0, amount))
  }
}

object StatBasedLikelihood{
  
  
}
