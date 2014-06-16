package game.tools

import net.davidashen.util.ErrorHandler
import net.davidashen.text.{Hyphenator => JHyphenator}

class Hyphenator {

  val errorHandler = new ErrorHandler(){
    def debug(x$1: String,x$2: String): Unit = ??? 
    def error(x$1: String): Unit = ??? 
    def exception(x$1: String,x$2: Exception): Unit = ??? 
    def info(x$1: String): Unit = ??? 
    def isDebugged(x$1: String): Boolean = ??? 
    def warning(x$1: String): Unit = ???
  }
  
  private val h = new JHyphenator();
  h.setErrorHandler(errorHandler);
  
  def loadTable(fileName:String):Unit = {
    h.loadTable(new java.io.BufferedInputStream(new java.io.FileInputStream(fileName)));
  }
  
  def hyphenate(word:String):Seq[String] = {
    val hyphenated = h.hyphenate(word)
    hyphenated.split("­")
  }
  
  
}