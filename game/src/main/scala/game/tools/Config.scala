package game.tools

trait Config {
  def elements:Seq[(String,Config)]
  def value:Seq[String]
}

class StringConfig(val value:Seq[String]) extends Config{
  val elements = Seq[(String,Config)]()
}

class ComplexConfig(val elements:Seq[(String,Config)]) extends Config{
  val value = Seq[String]()
}