package game.tools

object StringUtils {

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
  
  implicit class ReplaceVarsImplicit(val str:String) extends AnyVal{
    def replaceVars(vars:Map[String,String]) = {
      StringUtils.replaceVars(str, vars)
    }
  }
}