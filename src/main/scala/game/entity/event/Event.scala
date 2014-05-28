package game.entity.event

import game.random.Bag

class Event(val likelihood:EventLikelihood, 
    val variables:Map[String,EventVariable],
    val flavorText:Bag[String],
    val outcomes:Seq[EventOutcome]) {

}

object Event{
//  def apply(strRep:String):Event = {
//    
//    
//  }
}