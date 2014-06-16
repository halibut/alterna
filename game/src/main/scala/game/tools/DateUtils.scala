package game.tools

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateUtils {

  def fmtDate(date:DateTime, format:String):String = {
    DateTimeFormat.forPattern("MM-dd-yyyy").print(date)
  }
  
  implicit class DateConversionImplicit(val date:DateTime) extends AnyVal{
    def fmtDate(format:String = "MM-dd-yyyy"):String = {
      DateUtils.fmtDate(date, format)
    }
  }
}