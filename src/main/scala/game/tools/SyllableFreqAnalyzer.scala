package game.tools

import scala.collection.mutable.Map
import scala.collection.mutable.ArrayBuffer
import game.random.Bag

object SyllableFreqAnalyzer {

  val inputFile = "data/last-names.txt";
  val outputHyphen = "data/last-names-hyphen-freqs.txt";
  val outputChars = "data/last-names-2char-freqs.txt";
  
  val h = new Hyphenator()
  h.loadTable("data/hyphen.tex");
  
  def main(args:Array[String]):Unit = {
    
    val hyphenMap = Map[String,(String,Int)]();
    val twoCharMap = Map[String,Map[String,Int]]().withDefault(x => Map[String,Int]().withDefaultValue(0));
    //val twoCharArr = ArrayBuffer[(String,String)]()
    
    def addTo2CharMap(str1:String, str2:String):Unit = {
      val innerMap = twoCharMap(str1)
      innerMap.update(str2, innerMap(str2) + 1)
      twoCharMap.update(str1, innerMap)
    };
    
    for(name <- FileUtils.getFileLines(inputFile)){
      val nm = name.toLowerCase().trim();
      
      val hyphenated = h.hyphenate(nm);
      
      val firstChar = nm.head.toString
      addTo2CharMap("", firstChar);
      //twoCharArr.append(("",firstChar))
      
      
      val nmChars = new Array[Char](nm.length + 2)
      
      nmChars(0) = 0;
      nmChars(nmChars.length-1) = 0
      for(i <- 0 until nm.length){
        nmChars(i+1) = nm.charAt(i)
      }
      
      for(i <- 0 until (nmChars.length-2)) {
        val ch1:String = if(nmChars(i) == 0) "" else nmChars(i).toString;
        val ch2:String = nmChars(i+1).toString;
        val ch3:String = if(nmChars(i+2) == 0) "" else nmChars(i+2).toString;
        val key = ch1 + ch2;
        val value = ch3;
        
        addTo2CharMap(key, value);
        //twoCharArr.append((key,value))
      } 
    }
    
     val twoCharSeqMap = for(key:String <- twoCharMap.keySet) yield{
        (key, twoCharMap(key))
      }
      //val twoCharSeqMap = twoCharMap.toSeq.sortWith(_._1 < _._1)
     
     println(twoCharSeqMap.toSeq.sortWith(_._1 < _._1).mkString("\n"))
     
     val twoCharSeqBagsSeq:Seq[(String,Bag[String])] = twoCharMap.map{mp =>
       val bgValues = mp._2.toSeq
       (mp._1, new Bag[String](bgValues))
     }.toSeq
     
     val twoCharSeqBags = twoCharSeqBagsSeq.toMap
     
     for(i <- 0 until 200){
       var name = ""
       var continue = true;
       while(name.length() < 50 && continue){
         val last2Chars:String = if(name.length == 0){
           ""
         }
         else if(name.length == 1){
           name
         }
         else{
           name.takeRight(2)
         }
         val nextChar = twoCharSeqBags(last2Chars).get
         if(nextChar == ""){
           continue = false;
         }
         else{
           name += nextChar;
         }
       }
       
       println(name)
     }
      
      
      
      //val twoCharArrGroups = twoCharArr.groupBy(_._1)
      
      //println(twoCharArrGroups.toSeq.sortWith(_._1 < _._1).mkString("\n"))
    
  }
  
}