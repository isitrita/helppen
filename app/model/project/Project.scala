package model.project

import model.{Users, User}

case class Project(id : BigInt, name : String)

object Projects {

  val defaultProject = Project(1, "My Work")
  private val all = Map(
    Users.defaultUser -> Seq(defaultProject)
  )

  def find(user : User) : Option[Seq[Project]] = {
    all get user
  }
}
