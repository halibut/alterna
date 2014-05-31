package game.entity.event.outcome

import game.entity.event.likelihood.Likelihood
import game.entity.event.Event
import game.random.Bag
import game.entity.character.Character

abstract class Outcome(implicit val event:Event) {
  event.addOutcome(this)
  
  private var likelihood: Seq[Likelihood] = Seq();
  private[event] var flavorText: Bag[String] = Bag();
  private var results: Seq[Result] = Seq()
  
  def FlavorText(flavorText: String*): Unit = {
    this.flavorText = Bag.fromItems(flavorText:_*)
  }
  
  def DependsOn(likelihoods:Likelihood*):Unit ={
    this.likelihood ++= likelihoods
  }
  
  def Result(results:Result*):Unit = {
    this.results ++= results
  }
  
  def calcAttunement(char:Character):Double = {
    likelihood.map{l =>
      l.chance(char)
    }.sum / likelihood.size
  }
  
  def resolve(char:Character):Unit = {
    results.foreach{ result =>
      result.updateCharacter(char)
    }
  }
}

