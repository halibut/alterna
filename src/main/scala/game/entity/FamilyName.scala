package game.entity
import game.random.Bag

class FamilyName {
	var parts:List[String] = List();
	
	def name = {
	  val tmp = parts.mkString
	  if(tmp.size > 0){
	    tmp.head.toUpper + tmp.tail   
	  }
	  else{
	    ""
	  }
	}
}

object FamilyName {
    
    val syllables:Bag[String] = Bag(
      "tay","sa","stop","rad","ur","ure","thraw","threw",
      "sul","var","vil","up","ack","rip","gru","lesp","lol",
      "op","us","ram","bo","ith","stra","lil","lig","a","e",
      "i","o","u","y","wyv","lyv","pu","pa","pro","pra","pseu"
    )
    
    val altSyls:Bag[String] = Bag(
            "kra", "gu", "swe", "to", "bo", "ga", "stru", "pe", "ru", "ne", "sto", "le", "na"
            )
    
    val c1:Seq[String] = Seq(
            "","","","","","","","","","","","","","",
            "b","c","d","f","g","h","j","k","l","m","n","p","qu","s","t","v","w","x","y","z",
            "ch","st","th")
    
    //val c2:Seq[String] = Seq("","r")
    val c2:Seq[String] = Seq("")
    
    val v:Seq[String] = Seq("a","e","i","o","u",
            "a","e","i","o","u",
            "a","e","i","o","u",
            "a","e","i","o","u",
            "a","e","i",
            "ae","ie","ou","au","ee","ea","oo")
    
//    val c3:Seq[String] = Seq("","nk","rk","d")
//    val c4:Seq[String] = Seq("","s","");
    val c3:Seq[String] = Seq("")
    val c4:Seq[String] = Seq(
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "","","","","","","","","","","","","","","","","","","","",
            "s","nk","rk","d","ck",
            "b","c","f","g","h","j","k","l","m","n","p","s","t","v","w","x","y","z");
    
    def syls = new Bag((
      for(ca <- c1;
    	cb <- c2;
    	vc <- v;
    	cd <- c3;
    	ce <- c4) yield ((ca+cb+vc+cd+ce, 1))).toSeq)
    	
    	
    
    def generateName(syllableCount:Int):FamilyName = {
        val parts =
            for(i <- 0 until syllableCount) yield {
            	syls.get
            }
        
        val name = new FamilyName()
        name.parts = parts.toList;
        name
    }
    
}