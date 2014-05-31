package game.entity.character

import scala.language.{postfixOps}
import game.entity.character.CharacterSex._
import game.entity.event.LifeEvent
import game.entity.Location
import org.joda.time.DateTime
import game.entity.World

class Character {

  var sex: CharacterSex = Male
  var gender: Float = 0.25f;

  var name: (String, String) = ("", "")
  var inFamily: Option[Family] = None;
  var headOfFamily: Option[Family] = None;

  var birthDate: Option[DateTime] = None;
  var deathDate: Option[DateTime] = None;

  val lifeEvents = new CharacterLifeEvents()
  val relationships = new CharacterRelationships()
  val stats = new CharacterStats()
  val personality = new CharacterPersonality()
  var level:Int = 1

  var location: Option[Location] = None;
  var world: Option[World] = None

  def isDeceased: Boolean = deathDate.isDefined

  def basicInfo(includeLocation: Boolean = false): String = {
    fullName + " (" +
      age + " - " +
      (if (sex == Male) "M" else "F") +
      "/" +
      (if (gender <= 0.5f) "M" else "F") +
      ")" +
      (if (includeLocation)
        " - " + location.map(_.name).getOrElse("No Location")
      else
        "")
  }

  def firstName: String = {
    if (gender < 0.5f) {
      name._2
    } else {
      name._1
    }
  }
  def lastName: Option[String] = {
    for (fam <- headOfFamily) {
      return Some(fam.lastName)
    }
    for (fam <- inFamily) {
      return Some(fam.lastName)
    }
    return None
  }
  def fullName: String = {
    var fName = firstName
    if (lastName.isDefined) {
      fName = fName + " " + lastName.get;
    }
    fName
  }

  def setHeadOfFamily(family: Family): Unit = {
    if (headOfFamily isEmpty) {
      headOfFamily = Some(family)
      family.addHead(this)
    } else if (headOfFamily.get != family) {
      throw new IllegalStateException("Already the head of different family: " + lastName + ". You must remove character from that family first.")
    }
  }

  def addMember(family: Family): Unit = {
    if (inFamily isEmpty) {
      inFamily = Some(family)
      family.addMember(this)
    } else if (inFamily.get != family) {
      throw new IllegalStateException("Already in a different family: " + lastName + ". You must remove character from that family first.")
    }
  }

  def moveLocation(location: Location) = {
    this.location.foreach { oldLoc =>
      oldLoc.removeCharacter(this)
    }
    this.location = Some(location)
  }

  def age: Int = {
    val a = for (w <- world; b <- birthDate) yield {
      w.year.getYear() - b.getYear()
    }

    a.getOrElse(0)
  }

  
}