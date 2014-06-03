package game.entity.event


import game.random.Bag
import game.entity.character.Character
import game.entity.event.likelihood.Likelihood
import game.entity.event.outcome.Outcome
import game.entity.character.Stat
import game.entity.event.trigger.EventTrigger
import game.tools.StringUtils._

trait Event {
  implicit val event:Event = this
  
  protected var eventName: String = ""

  protected var triggeredBy: Seq[EventTrigger] = Seq();
  protected var variables: Seq[(String, EventVariable)] = Seq();
  protected var flavorText: Bag[String] = Bag();
  protected var outcomes: Seq[Outcome] = Seq();

  def TriggeredBy(likelihoods: EventTrigger*): Unit = {
    this.triggeredBy ++= likelihoods
  }

  def Variables(variables: (String, EventVariable)*): Unit = {
    this.variables ++= variables.toMap
  }

  def FlavorText(flavorText: String*): Unit = {
    this.flavorText = Bag.fromItems(flavorText:_*)
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
  
  protected def getFlavorText(vars:Map[String,String]):String = {
    val fText = flavorText.get.getOrElse("").replaceVars(vars,"<",">").capitalize
    
    fText.capitalizeSentences
  }
  
}

object Event{

  
}

