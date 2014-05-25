package game.tools

object FileUtils {

  def getFileLines(fileName:String):Seq[String] = {
    
    val source = scala.io.Source.fromFile(fileName)
    val lines = source.getLines.toArray.toSeq
	source.close()
	
	lines
  }
}