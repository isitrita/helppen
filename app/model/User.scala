package model

case class User(email : String, password : String) {
  override def hashCode(): Int = email.hashCode
  override def toString: String = email
  override def equals(obj: scala.Any): Boolean = obj.isInstanceOf[User] && obj.asInstanceOf[User].email == email
}

object Users {
  val defaultUser = User("user@mail.com","123")

  private val all = List(defaultUser)

  def findOneByUsername(email : String) = {
    all.find {_.email == email}
  }
}


