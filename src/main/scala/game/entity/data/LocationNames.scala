package game.entity.data
import game.entity.gen.OddsCalculator
import game.tools.FileUtils
import game.random.Bag
import game.random.Random

class LocationName(val baseName:String, 
    val prefix:Option[String] = None, val suffix:Option[String] = None) {
  
  def name = {
    prefix.getOrElse("") + baseName + suffix.getOrElse("")
  }
  
  override def toString():String = {
    name
  }
  
}

object LocationNames {
  
  def loadBag(fileName:String, trim:Boolean = true):Bag[String] = {
      val seq =  FileUtils.getFileLines(fileName)
      	.map(word => if(trim) word.trim() else word)
      	.filterNot(_.isEmpty())
      	.toSeq
      	
      new Bag[String](seq.map((_,1)))
    }

    lazy val nameBag:Bag[String] = loadBag("data/locations.txt")

    lazy val prefixBag:Bag[String] = loadBag("data/location-prefixes.txt", false)
    
    lazy val suffixBag:Bag[String] = loadBag("data/location-suffixes.txt", false)
    
    
    def getName(baseName:String = "", prefixSuffixOdds:Int = 3, prefixSuffixOutOf:Int = 4):LocationName = {
      val bName = if(baseName == "") nameBag.get else baseName
      
      var p:Option[String] = None
      if(Random.rand.hitOdds(prefixSuffixOdds, prefixSuffixOutOf)){
        p = Some(prefixBag.get)
      }
      var s:Option[String] = None
      if(Random.rand.hitOdds(prefixSuffixOdds, prefixSuffixOutOf)){
        s = Some(suffixBag.get)
      }
      
      new LocationName(bName, p, s)
    }
}