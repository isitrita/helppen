package model.card

import java.util.Date
import java.util.concurrent.atomic.AtomicReference

import model.project.Projects
import model.tags.Tag
import model.utils.DateUtils
import model.{User, Users}

/*
ID *
Проект (ID проекта) *
Тип (событие, напоминание, дело, заметка) *
Наименование *
Основной текст
Родительская сущность (ID)
Дочерние сущности (Ряд ID)
Положение относительно других сущностей - абсолютный приоритет *
Дата и время начала
Напоминание
  - Дата, время
  - Периодичность
Продолжительность (дней, часов, минут)
Метки (ID меток)
Периодичность повторения
Статус (активно, архив, готово) *
Дата создания *
Последнее изменение *
*/

trait Card {
  def id: BigInt
  def project: BigInt
  def name: String
  def text: String
  def priority: Long
  def tags: Seq[Tag]
  def status: Status
  def created: Date
  def modified: Date

  override def hashCode(): Int = id.hashCode()

  override def toString: String = name

  override def equals(obj: scala.Any): Boolean = obj.isInstanceOf[Card] && id == obj.asInstanceOf[Card].id
}

case class Deal(val id: BigInt,
                val project: BigInt,
                val name: String,
                val text: String,
                val priority: Long,
                val tags: Seq[Tag],
                val status: Status,
                val created: Date,
                val modified: Date,
                val duration : Long
                ) extends Card

case class Event(val id: BigInt,
                val project: BigInt,
                val name: String,
                val text: String,
                val priority: Long,
                val tags: Seq[Tag],
                val status: Status,
                val created: Date,
                val modified: Date,
                val startDate : Date,
                val startTime : Date,
                val duration : Long
                ) extends Card

case class Note(val id: BigInt,
                val project: BigInt,
                val name: String,
                val text: String,
                val priority: Long,
                val tags: Seq[Tag],
                val status: Status,
                val created: Date,
                val modified: Date
                ) extends Card

private object IdGen {

  private val id = new AtomicReference[BigInt](0)

  def next : BigInt = {
    var continue = true
    var update : BigInt = null
    while (continue) {
      val prev = id.get
      update = prev + 1
      continue = !id.compareAndSet(prev, update)
    }
    update
  }
}

object Cards {

  val dummyNote = Note(
    IdGen.next,
    Projects.defaultProject.id,
    "Note name1",
    "Note text",
    1,
    Seq.empty,
    Status.Active,
    new Date(),
    new Date()
  )

  val dummyDeal = Deal(
    IdGen.next,
    Projects.defaultProject.id,
    "Deal name1",
    "Deal text",
    2,
    Seq.empty,
    Status.Active,
    new Date(),
    new Date(),
    30 * 60 * 1000 // 30 minutes
  )

  val dummyDeal2 = Deal(
    IdGen.next,
    Projects.defaultProject.id,
    "Deal name2",
    "Deal text",
    3,
    Seq(Tag.dummyTag1),
    Status.Active,
    new Date(),
    new Date(),
    30 * 60 * 1000 // 30 minutes
  )

  val dummyEvent = Event(
    IdGen.next,
    Projects.defaultProject.id,
    "Event name1",
    "Deal text",
    4,
    Seq(Tag.dummyTag1),
    Status.Active,
    new Date(),
    new Date(),
    DateUtils.dayOf(new Date()),
    DateUtils.randomTime,
    30 * 60 * 1000 // 30 minutes
  )

  private val cards = Map(
    (Users.defaultUser, Seq(dummyNote, dummyDeal, dummyDeal2, dummyEvent))
  )

  def allBy(user : User) : Option[Seq[Card]] = cards get user
}
