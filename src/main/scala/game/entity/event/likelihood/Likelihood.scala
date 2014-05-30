package game.entity.event.likelihood

import game.entity.character.Character

trait Likelihood {
  def chance(character: Character): Double
}
