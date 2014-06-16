package game.entity.decision

import game.random.Bag

class RandomDecisionEngine extends DecisionEngine {
  
  def decide[C <: Choice](char:Character, choices:Seq[C]):Option[C] = {
    Bag.fromItems(choices:_*).get
  }

}