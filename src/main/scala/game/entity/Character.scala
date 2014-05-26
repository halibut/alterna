package game.entity
import java.util.Date

import game.entity.CharacterSex._

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
    val personality:CharacterPersonality = new CharacterPersonality()
    
    var location:Option[Location] = None;
    
    def isDeceased:Boolean = deathDate.isDefined
    
    def basicInfo(includeLocation:Boolean = false):String = {
      fullName + " ("+
      (if(sex == Male) "M" else "F") +
      " - " +
      (if(gender <= 0.5f) "M" else "F") +
      ")" + 
      (if(includeLocation)
        " - " + location.map(_.name ).getOrElse("No Location")
       else 
         ""
      )
    }
    
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
    
    def setHeadOfFamily(family:Family) = {
      if(headOfFamily isEmpty){
        headOfFamily = Some(family)
        family.addHead(this)
      }
      else if(headOfFamily.get != family){
        throw new IllegalStateException("Already the head of different family: "+ lastName +". You must remove character from that family first.")
      }
    }
    
    def addMember(family:Family) = {
      if(inFamily isEmpty){
        inFamily = Some(family)
        family.addMember(this)
      }
      else if(inFamily.get != family){
        throw new IllegalStateException("Already in a different family: "+ lastName +". You must remove character from that family first.")
      }
    }
}