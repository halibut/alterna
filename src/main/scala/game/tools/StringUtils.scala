package game.tools

object StringUtils {

  def replaceVars(str:String, vars:Map[String,String],prefix:String = "${",postfix:String = "}"):String = {
    var newStr = ""
    var ind = 0
    while(ind < str.length()){
      val tokenStart = str.indexOf(prefix,ind)
      if(tokenStart >= 0){
        val tokenEnd = str.indexOf(postfix,tokenStart)
        if(tokenEnd >= 0){
          val varName = str.substring(tokenStart+prefix.length,tokenEnd)
          val replacement = vars.getOrElse(varName,prefix+varName+postfix)
          newStr += str.substring(ind,tokenStart)
          newStr += replacement
          ind = tokenEnd + postfix.length
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
    def replaceVars(vars:Map[String,String], prefix:String = "${",postfix:String = "}") = {
      StringUtils.replaceVars(str, vars, prefix, postfix)
    }
  }
}