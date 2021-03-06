package game.entity.event.outcome

import game.entity.character.Stat
import game.entity.event.StatWeight
import game.entity.character.Character
import game.random.Random
import game.entity.character.Personality
import org.joda.time.DateTime

class PersonalityChangeResult(personalityAndWeight:(Personality,StatWeight)) extends Result{
  val (personality, weight) = personalityAndWeight

  def updateCharacter(character:Character, date:DateTime):Unit = {
    val change = weight match{
      case StatWeight.SlightPositive => Random.rand.randInt(1, 3)
      case StatWeight.ModeratePositive => Random.rand.randInt(3, 6)
      case StatWeight.HeavyPositive => Random.rand.randInt(6, 11)
      case StatWeight.SlightNegative => Random.rand.randInt(-2, 0)
      case StatWeight.ModerateNegative => Random.rand.randInt(-5, -2)
      case StatWeight.HeavyNegative => Random.rand.randInt(-10, -5)
      case _ => 0
    }
    character.personality(personality) = character.personality(personality) + change
  }
}