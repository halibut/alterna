package game.entity.decision

trait DecisionEngine {
  
  def decide[C <: Choice](char:Character, choices:Seq[C]):C

}