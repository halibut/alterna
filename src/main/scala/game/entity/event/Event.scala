package game.entity.event

import game.random.Bag
import game.entity.event.likelihood.Likelihood
import game.entity.event.outcome.Outcome
import game.entity.event.likelihood.YearlyChance
import game.entity.character.Stat
import game.entity.event.likelihood.StatBasedLikelihood

trait Event {
  implicit val event:Event = this
  
  private var eventName: String = ""

  private var likelihood: Seq[Likelihood] = Seq();
  private var variables: Map[String, EventVariable] = Map();
  private var flavorText: Bag[String] = Bag();
  private var outcomes: Seq[Outcome] = Seq();

  def Likelihood(likelihoods: Likelihood*): Unit = {
    this.likelihood ++= likelihoods
  }

  def Variables(variables: (String, EventVariable)*): Unit = {
    this.variables ++= variables.toMap
  }

  def FlavorText(flavorText: String*): Unit = {
    this.flavorText = new Bag[String](flavorText.map((_, 1)))
  }

  def addOutcome(outcome: Outcome): Unit = {
    //outcome.event = this
    this.outcomes :+= outcome
  }
  
  def Name:String = {
    this.eventName
  }
  def Name_=(eventName:String) = {
    this.eventName = eventName
  }
  
  def YearlyChance(chances:Int,outOf:Int):YearlyChance = new YearlyChance(chances, outOf);
  def Choose(options:String*):ChoiceVariable = new ChoiceVariable(options);
  
  def +(stat:Stat):Likelihood = {
    new StatBasedLikelihood(stat, StatWeight.SlightPositive)
  }
  def ++(stat:Stat):Likelihood = {
    new StatBasedLikelihood(stat, StatWeight.ModeratePositive)
  }
  def +++(stat:Stat):Likelihood = {
    new StatBasedLikelihood(stat, StatWeight.HeavyPositive)
  }
}

object Event{

  
}

