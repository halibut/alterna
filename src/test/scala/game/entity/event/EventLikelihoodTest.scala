package game.entity.event

import org.junit.Test
import org.junit.Assert._
import game.entity.event.likelihood.YearlyChanceLikelihood
import game.entity.event.likelihood.StatbasedLikelihood
import game.entity.event.likelihood.PersonalitybasedLikelihood
import game.entity.event.likelihood.MultipleLikelihood
import game.entity.event.likelihood.EventLikelihood

class EventLikelihoodTest {
  
  @Test
  def testParseString_StatBased(){
    val l = EventLikelihood("++Stat.Strength").asInstanceOf[MultipleLikelihood]
    
    assertTrue(l.likelihoodMethods.head.isInstanceOf[StatbasedLikelihood])
  }
  
  @Test
  def testParseString_PersonalityBased(){
    val l = EventLikelihood("--Personality.Satisfied").asInstanceOf[MultipleLikelihood]
    
    assertTrue(l.likelihoodMethods.head.isInstanceOf[PersonalitybasedLikelihood]) 
  }
  
  @Test
  def testParseString_YearlyChance(){
    val l = EventLikelihood("YearlyChance(1/30)").asInstanceOf[MultipleLikelihood]
    
    assertTrue(l.likelihoodMethods.head.isInstanceOf[YearlyChanceLikelihood]) 
  }
  
  @Test
  def testParseString_Multiple(){
    val l = EventLikelihood("YearlyChance(1/30)\n\t-Personality.Satisfied\n\t\n\t\t+Stat.Strength\n+++Stat.Defense").asInstanceOf[MultipleLikelihood]
    
    assertTrue(l.likelihoodMethods(0).isInstanceOf[YearlyChanceLikelihood])
    assertTrue(l.likelihoodMethods(1).isInstanceOf[PersonalitybasedLikelihood])
    assertTrue(l.likelihoodMethods(2).isInstanceOf[StatbasedLikelihood])
    assertTrue(l.likelihoodMethods(3).isInstanceOf[StatbasedLikelihood])
  }

}