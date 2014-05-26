package game.entity.decision

import game.random.Bag

class RandomDecisionEngine extends DecisionEngine {
  
  def decide[C <: Choice](char:Character, choices:Seq[C]):C = {
    new Bag[C](choices.map((_,1)).toSeq).get
  }

}