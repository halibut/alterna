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
  
  private var eventName: String = ""

  private var triggeredBy: Seq[EventTrigger] = Seq();
  private var variables: Seq[(String, EventVariable)] = Seq();
  private var flavorText: Bag[String] = Bag();
  private var outcomes: Seq[Outcome] = Seq();

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
  
  def isTriggered(character:Character):Boolean = {
    val triggers = this.triggeredBy.filter{l =>
      l.isTriggered(character)
    } 
    
    !triggers.isEmpty
  }
  
  def resolve(character:Character):LifeEvent = {
    val vars = EventVariables.getEventVars(variables, character) 
    
    val triggerFlavorText = flavorText.get.get.replaceVars(vars,"<",">").capitalize
    
    val attunements = outcomes.map{outcome =>
      outcome -> outcome.calcAttunement(character)
    }
    
    val outcome = Bag(attunements:_*).get.get
    val outcomeFlavorText = outcome.flavorText.get.get.replaceVars(vars,"<",">").capitalize
    outcome.resolve(character)
  
    LifeEvent(LifeEventType.PhysicalTrial,
        character.world.get.year,
        character,
        triggerFlavorText + " " + outcomeFlavorText
        )
    
  }
}

object Event{

  
}

