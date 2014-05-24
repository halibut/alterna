package game.entity.data
import game.entity.gen.OddsCalculator

object LocationNames {

    val names:Set[String] = Set(
    	"Florence",
    	"Prague",
    	"Tulsa",
    	"Beijing",
    	"Lima",
    	"Istanbul",
    	"Moscow",
    	"Cairo",
    	"Nairobi",
    	"Athens",
    	"La Paz",
    	"Dallas",
    	"Portland",
    	"Madrid",
    	"Sydney",
    	"Sweeney",
    	"Harwood",
    	"Paris",
    	"Denver",
    	"Tokyo"
    );
    
    val locPrefixes = new OddsCalculator[String](
    	"New "->1,
    	"Old "->1,
    	"Neo "->1,
    	"Quaint "->1,
    	"Yellow "->1,
    	"Fool's "->1,
    	"Broken "->1,
    	"Steamy "->1,
    	"Robo-"->1,
    	"Las "->1,
    	"Mount "->1,
    	"Constanti"->1,
    	"The Little Town of "->1,
    	""->5)
    
    val locSuffixes = new OddsCalculator[String](
    	" Shire"->1,
    	"burg"->1,
    	"ville"->1,
    	"ton"->1,
    	"wood"->1,
    	"iana"->1,
    	"ianapolis"->1,
    	"tinople"->1,
    	"ia"->1,
    	" York"->1,
    	" Vegas"->1,
    	" Hill"->1,
    	" Valley"->1,
    	" Canyon"->1,
    	" Springs"->1,
    	" Hill"->1,
    	" Mountain"->1,
    	" City"->1,
    	" Forest"->1,
    	"'s Point"->1,
    	"'s Oasis"->1,
    	"'s Meadow"->1,
    	""->5)
    
}