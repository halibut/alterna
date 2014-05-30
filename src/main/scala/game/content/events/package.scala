package game.content

import game.entity.event.likelihood.StatBasedLikelihood
import game.entity.event.StatWeight
import game.entity.character.Stat
import game.entity.event.outcome.StatChangeResult
import game.entity.character.Personality
import game.entity.event.likelihood.PersonalityBasedLikelihood
import game.entity.event.outcome.PersonalityChangeResult
import game.entity.event.outcome.StatChangeResult
import game.entity.event.outcome.StatChangeResult
import scala.language.{implicitConversions}

package object events {

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
    def +++(): (Stat, StatWeight) = (stat, StatWeight.HeavyPositive)
    def ++(): (Stat, StatWeight) = (stat, StatWeight.ModeratePositive)
    def +(): (Stat, StatWeight) = (stat, StatWeight.SlightPositive)
    def -(): (Stat, StatWeight) = (stat, StatWeight.SlightNegative)
    def --(): (Stat, StatWeight) = (stat, StatWeight.ModerateNegative)
    def ---(): (Stat, StatWeight) = (stat, StatWeight.HeavyNegative)
  }

  implicit class PersonalityToStatAndWeightImplicit(val personality: Personality) extends AnyVal {
    def +++(): (Personality, StatWeight) = (personality, StatWeight.HeavyPositive)
    def ++(): (Personality, StatWeight) = (personality, StatWeight.ModeratePositive)
    def +(): (Personality, StatWeight) = (personality, StatWeight.SlightPositive)
    def -(): (Personality, StatWeight) = (personality, StatWeight.SlightNegative)
    def --(): (Personality, StatWeight) = (personality, StatWeight.ModerateNegative)
    def ---(): (Personality, StatWeight) = (personality, StatWeight.HeavyNegative)
  }

}