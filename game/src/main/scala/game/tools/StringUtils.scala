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
  
  def capitalizeSentences(str:String):String = {
    var prev = ""
    for(ch <- str.trim) yield {
      val test = prev + ch
      val newChar = 
      if(prev.isEmpty){
        ch.toString.capitalize.head
      }
      else if(test.matches(".*?\\.\\.\\.\\s+[a-z]")){
        ch
      }
      else if(test.matches(".*?[\\.\\?\\!]\\)*\\s+[a-z]")){
        ch.toString.capitalize.head
      }
      else{
        ch
      }
      
      prev = test.takeRight(5)
      
      newChar
    }
  }
  
  implicit class StringUtilsImplicit(val str:String) extends AnyVal{
    def replaceVars(vars:Map[String,String], prefix:String = "${",postfix:String = "}"):String = {
      StringUtils.replaceVars(str, vars, prefix, postfix)
    }
    
    def capitalizeSentences:String = {
      StringUtils.capitalizeSentences(str)
    }
  }
}