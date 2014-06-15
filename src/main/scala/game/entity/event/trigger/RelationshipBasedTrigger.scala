package game.entity.event.trigger

import game.entity.character.RelationshipType
import game.entity.character.Character
import game.entity.event.StatWeight

class RelationshipBasedTrigger(val relTypeAndStrength:(RelationshipType,Option[StatWeight])) extends EventTrigger {
  val (relType,strength) = relTypeAndStrength

  override def isTriggered(td: TriggerData): Boolean = {
    td match {
      case tcd:TwoCharTriggerData => isTriggered(tcd.char1, tcd.char2)
      case ocd:OneCharTriggerData => isTriggered(ocd.character)
      case _ => false
    }
  }
  
  def isTriggered(character:Character):Boolean = {
    val rels = character.relationships(relType)
    !rels.isEmpty
  }
  
  def isTriggered(char1:Character, char2:Character):Boolean = {
    val rel = char1.relationships (char2, relType).map{r =>
      import StatWeight._
      strength.map{s =>
        s match {
          case HeavyNegative => r.strength < (2.0 * RelationshipType.MIN_VAL / 3.0) 
          case ModerateNegative => r.strength < (1.0 * RelationshipType.MIN_VAL / 3.0)
          case SlightNegative => r.strength < 0
          case SlightPositive => r.strength > 0
          case ModeratePositive => r.strength > (1.0 * RelationshipType.MAX_VAL / 3.0)
          case HeavyPositive => r.strength > (2.0 * RelationshipType.MAX_VAL / 3.0)
          case _ => false
        }
      }.getOrElse(true)
    }
    rel.getOrElse(false)
  }
}