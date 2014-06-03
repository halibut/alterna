package game.entity.event.trigger

import game.entity.character.Character

class MultiTrigger(val seq:Seq[EventTrigger], val requireAll:Boolean) extends EventTrigger {

  def isTriggered(character:Character):Boolean = {
    val triggerResults = seq.map(_.isTriggered(character))
    triggerResults.foldLeft(requireAll){case (v1,v2) =>
      if(requireAll){
        v1 && v2
      }
      else{
        v1 || v2
      }
    }
  }
  
  def and(trigger:EventTrigger):EventTrigger = new MultiTrigger(Seq(trigger,this), true)
  
  def or(trigger:EventTrigger):EventTrigger = new MultiTrigger(Seq(trigger,this), false)
  
  def Chance(double:Double):EventTrigger = new MultiTrigger(Seq(this,ChanceTrigger(double)),true)
  def Chance(chances:Int,outOf:Int):EventTrigger = {
    val prob = chances.toDouble / outOf.toDouble 
    new MultiTrigger(Seq(this,ChanceTrigger(prob)),true)
  }
}