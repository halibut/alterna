package game.entity.event.likelihood

import game.entity.character.Character
import game.entity.character.PersonalityDimension
import game.entity.event.StatWeight
import game.entity.character.CharacterPersonality


class PersonalityBasedLikelihood(val pDim: PersonalityDimension, weight: StatWeight) extends Likelihood {
  def chance(character: Character): Double = {
    import CharacterPersonality._
    val personality = character.personality
    val dimAmount = personality(pDim).toDouble * weight.getMultiplier()
    val amount = if (dimAmount < 0) -dimAmount / MinVal else dimAmount / MaxVal
    math.min(1.0, math.max(0.0, amount))
  }
}