package game.entity.character

import game.entity.event.LifeEvent

class CharacterLifeEvents {

  private var lifeEvents:Seq[LifeEvent] = Seq() 

  def add(event: LifeEvent): Unit = {
    this.lifeEvents = (lifeEvents :+ event)
      .sortWith((e1, e2) => (e1.date) isBefore (e2.date))
  }

  def apply(): Seq[LifeEvent] = {
    this.lifeEvents
  }
  
  override def toString():String = {
    this.lifeEvents.mkString("\n")
  }

}