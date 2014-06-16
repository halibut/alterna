package game.tools

import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer
import game.random.Bag
import game.random.Bag._
import scala.io.Source

object CharacterFreqAnalyzer {

  def writeAnalysisFile(file: String, charFreqMap: Map[String, Map[String, Int]]): Unit = {
    val charSeqMap = charFreqMap.toSeq.sortWith(_._1 < _._1)

    FileUtils.printToFile(file) { pw =>
      for (entry <- charSeqMap) {
        val key = entry._1
        val nextCharSeq = entry._2.toSeq.sortWith(_._1 < _._1).map {
          case (ch, freq) =>
            ch + " -> " + freq
        }
        pw.print(entry._1)
        pw.print(" => ")
        pw.println(nextCharSeq.mkString(", "))
      }
    }
  }

  def readAnalysisFile(file: String): Map[String, Map[String, Int]] = {
    val charMap = Map[String, Map[String, Int]]().withDefault(x => Map[String, Int]().withDefaultValue(0));

    for (line <- FileUtils.getFileLines(file) if (!line.trim().isEmpty())) {
      val arrowInd = line.indexOf("=>")
      val key = line.substring(0, arrowInd).trim()
      val freqs = line.substring(arrowInd + 2).trim().split("\\s*,\\s*");

      val nCharMap: Map[String, Int] = Map()

      val freqSeq = for (freqCount <- freqs) yield {
        val assocInd = freqCount.indexOf("->")
        val char = freqCount.substring(0, assocInd).trim()
        val count = freqCount.substring(assocInd + 2).trim().toInt

        nCharMap.put(char, count)
      }

      charMap.put(key, nCharMap)
    }

    charMap
  }

  def analyzeInputFile(fileName: String, charLength: Int = 3): Map[String, Map[String, Int]] = {
    val charMap = Map[String, Map[String, Int]]().withDefault(x => Map[String, Int]().withDefaultValue(0));

    def addToCharMap(str1: String, str2: String): Unit = {
      val innerMap = charMap(str1)
      innerMap.update(str2, innerMap(str2) + 1)
      charMap.update(str1, innerMap)
    };

    for (name <- FileUtils.getFileLines(fileName)) {
      val nm = name.toLowerCase().trim();

      val firstChar = nm.head.toString
      addToCharMap("", firstChar);

      val nmChars = new Array[Char](nm.length + charLength)

      nmChars(0) = 0;
      nmChars(nmChars.length - 1) = 0
      for (i <- 0 until nm.length) {
        nmChars(i + charLength - 1) = nm.charAt(i)
      }

      for (i <- 0 until (nmChars.length - charLength)) {
        var key: String = "";
        for (j <- 0 until charLength; if (nmChars(i + j) != 0)) {
          key += nmChars(i + j);
        }

        val value: String = if (nmChars(i + charLength) == 0) "" else nmChars(i + charLength).toString;

        addToCharMap(key, value);
      }
    }

    charMap
  }

  def main(args: Array[String]): Unit = {

    val analyzeChars = 3
    val inputFile = "data/last-names.txt";
    val outputChars = "data/last-names-" + analyzeChars + "char-freqs.txt";

    val charMapInit = analyzeInputFile(inputFile, analyzeChars)

    writeAnalysisFile(outputChars, charMapInit)

    val charMap = readAnalysisFile(outputChars)

    val charSeqMap = for (key: String <- charMap.keySet) yield {
      (key, charMap(key))
    }

    val twoCharSeqBagsSeq: Seq[(String, Bag[String])] = charMap.map { mp =>
      val bgValues = mp._2.toSeq
      (mp._1, Bag.fromProbs(bgValues:_*))
    }.toSeq

    val twoCharSeqBags = twoCharSeqBagsSeq.toMap

    for (i <- 0 until 200) {
      var name = ""
      var continue = true;
      while (name.length() < 50 && continue) {
        val lastNChars: String = name.takeRight(analyzeChars)
        val nextChar = twoCharSeqBags(lastNChars).get.get
        if (nextChar == "") {
          continue = false;
        } else {
          name += nextChar;
        }
      }

      println(name)
    }
  }

}