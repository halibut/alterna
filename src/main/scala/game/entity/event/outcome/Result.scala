package game.entity.event.outcome

import game.entity.character.Character
import org.joda.time.DateTime

trait Result {
  
  def updateCharacter(character:Character,eventDate:DateTime):Unit
  
}