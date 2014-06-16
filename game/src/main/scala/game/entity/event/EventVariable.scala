package game.entity.event

import game.random.Bag
import game.tools.StringUtils._
import game.entity.character.Character
import game.entity.character.CharacterSex._

trait EventVariable {
  def getVariable(vars:Map[String,String]):String
}

class StringVariable(str:String) extends EventVariable {
  def getVariable(vars:Map[String,String]):String = {
    str.replaceVars(vars,"<",">")
  }
}

class ChoiceVariable(choiceStrings:Seq[String]) extends EventVariable {
  val bag = Bag.fromItems(choiceStrings:_*)
  
  def getVariable(vars:Map[String,String]):String = {
    bag.get.get.replaceVars(vars,"<",">")
  }
}


object EventVariables{
  
  def getEventVars(varSeq:Seq[(String,EventVariable)], char:Character):Map[String,String] = {
    var vars = Map[String,String]()
    
    val s = char.sex
    vars += "reflexive-pronoun" -> s.getReflexivePronoun()
    vars += "object-pronoun" -> s.getObjectPronoun()
    vars += "subject-pronoun" -> s.getSubjectPronoun()
    vars += "possessive-pronoun" -> s.getPosessivePronoun()
    vars += "name" -> char.firstName
    
    for(entry <- varSeq){
      vars += entry._1 -> entry._2.getVariable(vars)
    }
     
    vars
  }
}
