package game.entity.data

import game.random.Bag
import game.random.Bag._
import game.tools.FileUtils

object CharacterNames {

  lazy val names: Seq[(String, String)] = {
    val lines = FileUtils.getFileLines("data/first-name-pairs.txt")
    val pairs = for(line <- lines; if(line.contains(","))) yield {
      val parts = line.split("\\s*,\\s*")
      (parts(0),parts(1))
    }
    
    pairs.toSeq
  }

  val nameBag = Bag.fromItems(names:_*)
  
}