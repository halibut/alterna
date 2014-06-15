package game.entity.event.trigger

import game.entity.character.Character

trait EventTrigger {

  def isTriggered(td: TriggerData): Boolean

  def Chance(d: Double) = new MultiTrigger(Seq(this, new ChanceTrigger(d)), true)
  def Chance(chances: Int, outOf: Int) = new MultiTrigger(Seq(this, new ChanceTrigger(chances.toDouble / outOf.toDouble)), true)

  def &&(otherTrigger: EventTrigger) = new MultiTrigger(Seq(this, otherTrigger), true)
  def ||(otherTrigger: EventTrigger) = new MultiTrigger(Seq(this, otherTrigger), false)
  def unary_! = new NotTrigger(this)

}


sealed trait TriggerData
class OneCharTriggerData(val character:Character) extends TriggerData;
class TwoCharTriggerData(val char1:Character, val char2:Character) extends OneCharTriggerData(char1);