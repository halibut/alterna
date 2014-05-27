package game.entity.event

import game.random.Bag

trait EventVariable {

  def getVariable(vars:Map[String,String]):String
}

class StringVariable(str:String) extends EventVariable {
  def getVariable(vars:Map[String,String]):String = {
    EventVariable.replaceVars(str, vars)
  }
}

class ChoiceVariable(choiceStrings:Seq[String]) extends EventVariable {
  val bag = new Bag[String](choiceStrings.map((_,1)))
  
  def getVariable(vars:Map[String,String]):String = {
    EventVariable.replaceVars(bag.get, vars)
  }
}


object EventVariable{
  def replaceVars(str:String, vars:Map[String,String]):String = {
    var newStr = ""
    var ind = 0
    while(ind < str.length()){
      val tokenStart = str.indexOf("${",ind)
      if(tokenStart >= 0){
        val tokenEnd = str.indexOf("}",tokenStart)
        if(tokenEnd >= 0){
          val varName = str.substring(tokenStart+2,tokenEnd)
          val replacement = vars.getOrElse(varName,"${"+varName+"}")
          newStr += str.substring(ind,tokenStart)
          newStr += replacement
          ind = tokenEnd + 1
        }
        else{
          newStr += str.substring(ind)
          ind = str.length()
        }
      }
      else{
        newStr += str.substring(ind)
        ind = str.length()
      }
    }
    newStr
  }
}