package game.entity.event.outcome

import game.entity.character.Character

trait Result {
  def updateCharacter(character:Character):Unit 
}