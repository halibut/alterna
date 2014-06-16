package game.entity.event

import game.entity.character.Character
import game.random.Bag
import game.tools.StringUtils._
import org.joda.time.DateTime
import game.entity.character.RelationshipType
import game.entity.event.trigger.EventTrigger
import game.entity.event.trigger.RelationshipBasedTrigger
import game.entity.event.trigger.MultiTrigger
import game.entity.event.trigger.NotTrigger
import game.entity.event.trigger.TriggerData
import game.entity.event.trigger.TwoCharTriggerData
import game.entity.event.trigger.OneCharTriggerData
import game.entity.event.trigger.TwoCharTriggerData
import game.entity.event.outcome.Outcome
import game.entity.event.outcome.Result
import game.entity.character.Relationship
import game.entity.event.outcome.TriggerRelationshipEventResult
import game.entity.event.outcome.TriggerRelationshipEventResult
import game.entity.event.outcome.OtherRelation

trait RelationshipEvent extends Event {

  protected var notTriggerdIf: Seq[EventTrigger] = Seq();

  case class Other(val trigger: EventTrigger) extends EventTrigger {
    override def isTriggered(td: TriggerData): Boolean = {
      td match {
        case tcd: TwoCharTriggerData => trigger.isTriggered(new OneCharTriggerData(tcd.char1))
        case _ => false
      }
    }
  }

  case class PersonalityAlign(val minAlign: Double) extends EventTrigger {
    override def isTriggered(td: TriggerData): Boolean = {
      td match {
        case tcd: TwoCharTriggerData => {
          val align = tcd.char1.personality alignment tcd.char2.personality
          align > minAlign
        }
        case _ => false
      }
    }
  }

  case class AddRelationship(relType: RelationshipType) extends Result {
    var other: Option[Character] = None
    override def updateCharacter(character: Character, eventDate: DateTime): Unit = {
      other.foreach { o =>
        val evt1 = LifeEvent(LifeEventType.NewRelationship, eventDate, character, "Friends with " + o.fullName + ".")
        character.relationships.add(Relationship(evt1, character, o, relType, 100))
        character.lifeEvents.add(evt1)
      }
    }
  }

  def NotTriggeredIf(likelihoods: EventTrigger*): Unit = {
    this.notTriggerdIf ++= likelihoods
  }

  def isTriggered(character: Character, character2: Character): Boolean = {
    if (canTrigger(character, character2)) {
      val td = new TwoCharTriggerData(character, character2)

      val triggers = this.triggeredBy.filter { l =>
        l.isTriggered(td)
      }

      !triggers.isEmpty
    } else {
      false
    }
  }

  def canTrigger(character: Character, character2: Character): Boolean = {
    val td = new TwoCharTriggerData(character, character2)

    val triggerMap = this.notTriggerdIf.map { t =>
      t.isTriggered(td)
    }
    triggerMap.filter(x => x).isEmpty
  }

  def resolve(toChar: Character, fromChar: Character, date: Option[DateTime] = None): Option[LifeEvent] = {
    val relType = toChar.relationships(fromChar).headOption
      .map(_.relationshipType.getObjectOfRelationship())
      .getOrElse("who-the-hell-knows-what-they-were")

    Variables(
      "relationship-type" -> new StringVariable(relType),
      "relationship-name" -> new StringVariable(fromChar.firstName))

    val vars = EventVariables.getEventVars(variables, toChar)

    val triggerFlavorText = getFlavorText(vars)

    val attunements = outcomes.map { outcome =>
      outcome -> outcome.calcAttunement(toChar)
    }

    val eventDate = date.getOrElse(toChar.world.get.year).hourOfDay().addToCopy(1)

    val outcome = Bag(attunements: _*).get.get
    val outcomeFlavorText = outcome.getFlavorText(vars)

    //Add the other character to the AddRelationship and TriggerRelationship outcomes
    for (result <- outcome.getResults) {
      result match {
        case addR: AddRelationship => addR.other = Some(fromChar)
        case tRel: TriggerRelationshipEventResult => {
          tRel.affects match {
            case or: OtherRelation => or.other = Some(fromChar)
            case _ => {}
          }
        }
        case _ => {}
      }
    }
    outcome.resolve(toChar, eventDate)

    Some(LifeEvent(LifeEventType.RelationshipChange,
      eventDate,
      toChar,
      triggerFlavorText + " " + outcomeFlavorText))
  }
}

