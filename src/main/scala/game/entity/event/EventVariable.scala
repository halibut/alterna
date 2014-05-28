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
    str.replaceVars(vars)
  }
}

class ChoiceVariable(choiceStrings:Seq[String]) extends EventVariable {
  val bag = new Bag[String](choiceStrings.map((_,1)))
  
  def getVariable(vars:Map[String,String]):String = {
    bag.get.replaceVars(vars)
  }
}


object EventVariables{
  def apply(strRep:String):Seq[(String,EventVariable)] = {
    val lines = strRep.split("\n").map(_.trim()).filterNot(_.size == 0)
    lines.map{line =>
      val eqInd = line.indexOf("=")
      if(eqInd < 0){
        throw new IllegalArgumentException("Could not parse variable from line: "+line)
      }
      val varName = line.substring(0,eqInd).trim()
      val varVal = line.substring(eqInd+1).trim()
      val variable = 
        if(varVal.toLowerCase.startsWith("choose(") && varVal.endsWith(")")){
          val indStart = varVal.indexOf("(")
          val vars = varVal.substring(indStart+1,varVal.size-1).trim.split("\\s*,\\s*")
          new ChoiceVariable(vars)
        }
        else{
          new StringVariable(varVal)
        }
      
      (varName,variable)
    }
  }
  
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
