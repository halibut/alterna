package game.entity.character

import scala.language.{ postfixOps }
import game.entity.character.CharacterSex._
import game.entity.event.LifeEvent
import game.entity.Location
import org.joda.time.DateTime
import game.entity.World
import game.random.Random

class Character {

  var sex: CharacterSex = Male
  var gender: Float = 0.25f;
  var quality: CharacterQuality = CharacterQuality.C

  var name: (String, String) = ("", "")
  var inFamily: Option[Family] = None;
  var headOfFamily: Option[Family] = None;

  var birthDate: Option[DateTime] = None;
  var deathDate: Option[DateTime] = None;

  val lifeEvents = new CharacterLifeEvents()
  val relationships = new CharacterRelationships()
  val stats = new CharacterStats()
  val personality = new CharacterPersonality()
  var level: Int = 0

  var location: Option[Location] = None;
  var world: Option[World] = None

  def isDeceased: Boolean = deathDate.isDefined

  def basicInfo(includeLocation: Boolean = false): String = {
    fullName + " [" + quality.name() + "] (" +
      age + " - " +
      (if (sex == Male) "M" else "F") +
      "/" +
      (if (gender <= 0.5f) "M" else "F") +
      ")" +
      (if (isDeceased) " - Deceased" else "") +
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
    //First try death-birth
    val a =
      for (d <- deathDate; b <- birthDate) yield (
        d.getYear() - b.getYear())

    //Then try currentdate - birth
    a.getOrElse {
      val a2 = for (w <- world; b <- birthDate) yield (
        w.year.getYear() - b.getYear())
      a2.getOrElse(0)
    }

  }

  private def lvlStat(stat: Stat, max: Int, min: Int) {
    import Stat._
    val adjMin = stat match {
      case MaxHP => 10
      case MaxAP => 1
      case _ => 0
    }
    val adj = stat match {
      case MaxHP => 5
      case MaxAP => 2
      case _ => 1
    }

    val newMax = adjMin + ((max + 1) * adj).asInstanceOf[Int]
    val newMin = adjMin + min

    val value = stats(stat) + Random.rand.randInt(newMin, newMax)
    stats(stat) = value
  }

  def levelUp: Unit = {
    val sortedStats = stats.stats.groupBy { s =>
      s._2.toDouble / s._1.getMax().toDouble
    }.toSeq.sortWith { (s1, s2) =>
      s1._1 > s2._1
    }.map { grp =>
      grp._2.map(_._1)
    }

    for (stat <- sortedStats.head) {
      lvlStat(stat, quality.getpStatIncMax(), quality.getpStatIncMin())
    }

    if (!sortedStats.tail.isEmpty) {
      for (stat <- sortedStats.tail.head) {
        lvlStat(stat, quality.getsStatIncMax(), quality.getsStatIncMin())
      }

      if (!sortedStats.tail.tail.isEmpty) {
        for (stat <- sortedStats.tail.tail.flatten) {
          lvlStat(stat, quality.getoStatIncMax(), quality.getoStatIncMin())
        }
      }
    }

    level += 1
  }

}