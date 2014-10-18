package model.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import scala.util.Random

object DateUtils {

  def dayOf(date : Date) : Date = {
    val c = Calendar.getInstance
    c.setTime(date)
    c.set(Calendar.HOUR_OF_DAY, 0)
    c.set(Calendar.MINUTE, 0)
    c.set(Calendar.SECOND, 0)
    c.set(Calendar.MILLISECOND, 0)
    c.getTime
  }

  def randomTime : Date = {
    val c = Calendar.getInstance()
    c.set(Calendar.YEAR, 0)
    c.set(Calendar.DAY_OF_YEAR, 0)
    c.set(Calendar.HOUR_OF_DAY, Random.nextInt(24))
    c.set(Calendar.MINUTE, Random.nextInt(60))
    c.set(Calendar.SECOND, 0)
    c.set(Calendar.MILLISECOND, 0)
    c.getTime
  }

  def dateToString(date : Date) : String = {
    val format = new SimpleDateFormat("dd MMMMM yyyy")
    format.format(date)
  }

  def timeToString(date : Date) : String = {
    val format = new SimpleDateFormat("HH:mm")
    format.format(date)
  }
}
