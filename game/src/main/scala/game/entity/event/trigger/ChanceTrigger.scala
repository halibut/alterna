package game.entity.event.trigger

import game.entity.character.Character
import game.entity.event.likelihood.Likelihood
import game.random.Random

case class ChanceTrigger(probability: Double) extends EventTrigger {

  def isTriggered(td: TriggerData): Boolean = {
    Random.rand.randDouble <= probability
  }

}

