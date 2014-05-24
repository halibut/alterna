package game.entity
import java.util.Date

class Character {
    
    var sex:CharacterSex = Male
    var gender:Float = 0.25f;
    
    var name:(String,String) = ("","")
    var inFamily:Option[Family] = None;
    var headOfFamily:Option[Family] = None;
        
    var birthDate:Option[Date] = None;
    var deathDate:Option[Date] = None;
    
    var lifeEvents:List[LifeEvent] = List();
    
    var relationships:Map[Character, List[CharacterRelationship]] = Map();
 
    val stats:CharacterStats = new CharacterStats();
    
    var location:Option[Location] = None;
    
    def isDeceased:Boolean = deathDate.isDefined
    
    def firstName:String = {
        if(gender < 0.5f){
            name._2
        }
        else{
            name._1
        }
    }
    def lastName:Option[String] = {
        for(fam <- headOfFamily){
            return Some(fam.lastName)
        }
        for(fam <- inFamily){
            return Some(fam.lastName)
        }
        return None
    }
    def fullName:String = {
        var fName = firstName
        if(lastName.isDefined){
            fName = fName + " " + lastName.get;
        }
        fName
    }
    
}