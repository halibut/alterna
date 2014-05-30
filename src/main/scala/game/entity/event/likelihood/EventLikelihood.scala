package game.entity.event.likelihood

import game.entity.character.Character
import game.entity.character.StatType
import game.entity.character.PersonalityDimension
import game.entity.character.CharacterPersonality.MaxVal
import game.entity.character.CharacterPersonality.MinVal
import game.entity.event.StatWeight
import game.entity.character.CharacterPersonality

trait Likelihood {
  def chance(character: Character): Double
}
