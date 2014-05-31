package game.entity.event.likelihood

import game.entity.character.Character
import game.entity.character.Personality
import game.entity.event.StatWeight
import game.entity.character.CharacterPersonality


class PersonalityBasedLikelihood(personalityAndWeight:(Personality,StatWeight)) extends Likelihood {
  val (pDim,weight) = personalityAndWeight
  
  def chance(character: Character): Double = {
    val personality = character.personality
    val dimAmount = personality(pDim).toDouble * weight.getMultiplier()
    val amount = if (dimAmount < 0) -dimAmount / pDim.getMin() else dimAmount / pDim.getMax()
    math.min(1.0, math.max(0.0, amount))
  }
}