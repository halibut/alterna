package game.tools

import java.util.IllegalFormatException

object FileUtils {

  def getFileLines(fileName: String): Seq[String] = {

    val source = scala.io.Source.fromFile(fileName)
    val lines = source.getLines.toArray.toSeq
    source.close()

    lines
  }

  def printToFile(fileName: String)(op: java.io.PrintWriter => Unit) {
    val f = new java.io.File(fileName)
    f.delete()
    val p = new java.io.PrintWriter(f)
    try {
      op(p)
    } finally {
      p.close()
    }
  }
  
  def readConfig(fileName: String):Config = {
    val lines = getFileLines(fileName)
    
    readConfig(lines)
  }
  
  def readConfig(lines:Seq[String], startLine:Int = 1):Config = {
    var properties = Seq[(String,Config)]()
    
    var curElement:Seq[String] = Seq()
    var curProp:String = null
    var curLine = startLine
    for{
        fileLine <- lines.zipWithIndex;
        val lineNo = fileLine._2 + startLine;
        val line = fileLine._1.trim();
        if (!line.isEmpty())
    }{
      
      if(curProp == null){
        val colonInd = line.indexOf(":")
        if(curElement.isEmpty && colonInd < 0){
          curElement :+= line
        }
        else{  
 	        curProp = line.substring(0,colonInd).trim()
	        val remainder = line.substring(colonInd+1).trim()
	        
	        if(remainder.startsWith("{")){
	          if(remainder.length() > 1){
	            val err = s"Could not parse line ${lineNo}. Expected nothing after the opening curly brace ({), but found additional text: ${remainder.substring(1)}. - ${line}";
	            throw new IllegalArgumentException(err)
	          }
	          else{
	            curElement = Seq[String]()
	          }
	        }
	        else{
	          properties = properties :+ (curProp,new StringConfig(Seq(remainder)))
	          curProp = null
	        }
	      }
      }
      
      else if(curProp != null && curElement != null){
        if(line == "}"){
          properties = properties :+ (curProp, readConfig(curElement, lineNo))
          curProp = null
          curElement = null
        }
        else{
          curElement = curElement :+ (line)
        }
      }
      
      else{
        
      }
    }
    
    new ComplexConfig(properties)
  }
}