package model.card

sealed case class Status(name : String)

object Status {
  val Archive = Status("archive")
  val Active =  Status("active")
  val Done = Status("done")
}
