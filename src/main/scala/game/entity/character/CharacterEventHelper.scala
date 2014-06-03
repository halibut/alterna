package game.entity.character

import org.joda.time.DateTime
import game.entity.event.LifeEvent
import game.entity.event.LifeEventType._
import game.entity.character.RelationshipType._
import game.entity.data.CharacterLevelRates
import game.random.Random

object CharacterEventHelper {

  def marry(p1: Character, p2: Character, date: DateTime, s1: Int, s2: Int) = {
    val m1 = LifeEvent(NewRelationship, date, p1, "Got married to " + p2.fullName)
    val m2 = LifeEvent(NewRelationship, date, p2, "Got married to " + p1.fullName)
    p1.lifeEvents.add(m1)
    p1.relationships.add(Relationship(m1, p1, p2, MarriedTo, s1))
    p2.lifeEvents.add(m2)
    p2.relationships.add(Relationship(m2, p2, p1, MarriedTo, s2))
  }
  
  def birth(family:Option[Family], child:Character) = {
    if(child.age >= 0){
	    for(age <- 0 until child.age;
	    	lvls <- 0 until CharacterLevelRates.rates(age)){
	      child.levelUp
	    }
    }
    
    val birthEvent = LifeEvent(Birth, child.birthDate.get, child, "Baby "+child.firstName+" was born!");
    child.lifeEvents.add(birthEvent)
    
    family.foreach{f => 
	    //Set parent/child relationships
	    for(p <- f.heads.filterNot(_.isDeceased)){
	      val parentEvent = LifeEvent(NewRelationship, child.birthDate.get, child, "Child "+child.firstName+" was born!")
	      p.lifeEvents.add(parentEvent)
	      p.relationships.add(Relationship(parentEvent, p, child, ParentOf, Random.rand.randInt(100,255)))
	      child.relationships.add(Relationship(birthEvent, child, p, ChildOf, Random.rand.randInt(100,255)))
	    }
	    
	    //Set sibling relationships
	    for(s <- f.children.filterNot(_.isDeceased)){
	      val siblingEvent = LifeEvent(NewRelationship, child.birthDate.get, child, "Sibling "+child.firstName+" was born!")
	      s.lifeEvents.add(siblingEvent)
	      s.relationships.add(Relationship(siblingEvent, s, child, SiblingsWith, Random.rand.randInt(50,255)))
	      child.relationships.add(Relationship(birthEvent, child, s, SiblingsWith, Random.rand.randInt(50,255)))
	    }
	    
	    f.addMember(child)
    }
  }

}