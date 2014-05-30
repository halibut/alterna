package game.entity.event.outcome

import game.entity.event.likelihood.Likelihood
import game.entity.event.Event
import game.random.Bag

abstract class Outcome(implicit val event:Event) {
  event.addOutcome(this)
  
  private var likelihood: Seq[Likelihood] = Seq();
  private var flavorText: Bag[String] = Bag();
  
  def FlavorText(flavorText: String*): Unit = {
    this.flavorText = new Bag[String](flavorText.map((_, 1)))
  }
  
}

