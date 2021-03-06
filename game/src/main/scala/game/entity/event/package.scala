package game.entity

import scala.language.{implicitConversions}
import game.entity.character.Stat
import game.entity.character.Personality
import game.entity.event.StatWeight
import game.entity.event.likelihood._
import game.entity.event.outcome._
import game.entity.event.trigger._
import game.entity.character.RelationshipType

package object event {
  
  //Triggers
  def AnotherEvent:EventTrigger = NeverTrigger;
  
  def Chance(chances:Int,outOf:Int):Double = chances.toDouble / outOf.toDouble;
  
  def Choose(options:String*):ChoiceVariable = new ChoiceVariable(options);
  
  def Age(from:Int,to:Int) = new AgeTrigger(from, to)
  
  def YearlyChance(prob:Double):ChanceTrigger = new ChanceTrigger(prob);
  def YearlyChance(chances:Int, outOf:Int):ChanceTrigger = YearlyChance(Chance(chances,outOf));
  
  //Implicit conversions
  implicit def triggerToMultiTrigger(trigger:EventTrigger) = new MultiTrigger(Seq(trigger),true) 
  implicit def relationshipToTrigger(relType:RelationshipType) = new RelationshipBasedTrigger((relType,None))
  
  implicit def statAndWeightToLikelihood(statAndWeight: (Stat, StatWeight)): StatBasedLikelihood = {
    new StatBasedLikelihood(statAndWeight)
  }

  implicit def statAndWeightToResult(statAndWeight: (Stat, StatWeight)): StatChangeResult = {
    new StatChangeResult(statAndWeight)
  }
  
  implicit def personalityAndWeightToLikelihood(personalityAndWeight: (Personality, StatWeight)): PersonalityBasedLikelihood = {
    new PersonalityBasedLikelihood(personalityAndWeight)
  }

  implicit def personalityAndWeightToResult(personalityAndWeight: (Personality, StatWeight)): PersonalityChangeResult = {
    new PersonalityChangeResult(personalityAndWeight)
  }

  implicit class StatToStatAndWeightImplicit(val stat: Stat) extends AnyVal {
    def +++ : (Stat, StatWeight) = (stat, StatWeight.HeavyPositive)
    def ++ : (Stat, StatWeight) = (stat, StatWeight.ModeratePositive)
    def + : (Stat, StatWeight) = (stat, StatWeight.SlightPositive)
    def - : (Stat, StatWeight) = (stat, StatWeight.SlightNegative)
    def -- : (Stat, StatWeight) = (stat, StatWeight.ModerateNegative)
    def --- : (Stat, StatWeight) = (stat, StatWeight.HeavyNegative)
    
    def +++(chance:Double) = new StatBasedTrigger((stat, StatWeight.HeavyPositive),chance)
    def ++(chance:Double) = new StatBasedTrigger((stat, StatWeight.ModeratePositive),chance)
    def +(chance:Double) = new StatBasedTrigger((stat, StatWeight.SlightPositive),chance)
    def -(chance:Double) = new StatBasedTrigger((stat, StatWeight.SlightNegative),chance)
    def --(chance:Double) = new StatBasedTrigger((stat, StatWeight.ModerateNegative),chance)
    def ---(chance:Double) = new StatBasedTrigger((stat, StatWeight.HeavyNegative),chance)
  }

  implicit class PersonalityToStatAndWeightImplicit(val personality: Personality) extends AnyVal {
    def +++ = (personality, StatWeight.HeavyPositive)
    def ++ = (personality, StatWeight.ModeratePositive)
    def + = (personality, StatWeight.SlightPositive)
    def - = (personality, StatWeight.SlightNegative)
    def -- = (personality, StatWeight.ModerateNegative)
    def --- = (personality, StatWeight.HeavyNegative)
    
    def +++(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.HeavyPositive),chance)
    def ++(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.ModeratePositive),chance)
    def +(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.SlightPositive),chance)
    def -(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.SlightNegative),chance)
    def --(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.ModerateNegative),chance)
    def ---(chance:Double) = new PersonalityBasedTrigger((personality, StatWeight.HeavyNegative),chance)
  }
  
  implicit class TriggerImplicitOps(val trigger:EventTrigger) extends AnyVal {
    def Chance(d:Double) = new MultiTrigger(Seq(trigger,new ChanceTrigger(d)),true)
    def Chance(chances:Int,outOf:Int) = new MultiTrigger(Seq(trigger,new ChanceTrigger(chances.toDouble / outOf.toDouble)),true)
    
    def &&(otherTrigger:EventTrigger) = new MultiTrigger(Seq(trigger,otherTrigger),true)
    def ||(otherTrigger:EventTrigger) = new MultiTrigger(Seq(trigger,otherTrigger),false)
    def unary_! = new NotTrigger(trigger)
  }

  
  implicit def eventAndRelationsToOutcome(eventAndRelations:(RelationshipEvent,Affects)):Result = {
    new TriggerRelationshipEventResult(eventAndRelations)
  }
  
  implicit def eventAndRelationsToOutcome(event:CharacterEvent):Result = {
    new TriggerCharacterEventResult(event)
  }
  
  def AllRelations:Affects = new CharacterRelations(RelationshipType.values:_*)
  def OtherCharacter:Affects = new OtherRelation()

}