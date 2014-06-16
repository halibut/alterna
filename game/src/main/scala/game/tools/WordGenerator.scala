package game.tools

import game.random.Random
import game.random.Bag

class WordGenerator(charfreqListFile: String, charSeqSize:Int, rnd: Random = new Random()) {

  val charMapBags = CharacterFreqAnalyzer.readAnalysisFile(charfreqListFile).map { mp =>
    val bgValues = mp._2.toSeq
    (mp._1, Bag.fromProbs(bgValues:_*))
  }.toSeq.toMap

  def getWord(maxLength: Int = 30): String = {
    var name = ""
    var continue = true;
    while (name.length() < maxLength && continue) {
      val lastNChars: String = name.takeRight(charSeqSize)
      val nextChar = charMapBags(lastNChars).get.get
      if (nextChar == "") {
        continue = false;
      } else {
        name += nextChar;
      }
    }
    name
  }
}

object WordGenerator {

}