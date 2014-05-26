package game.tools

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
}