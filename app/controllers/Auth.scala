package controllers

import model.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller, Security}

object Auth extends Controller {

  val userForm = Form(
    mapping(
      "email" -> text,
      "password" -> text
    ) (User.apply) (User.unapply)
  )

  def login = Action { implicit request =>
    Ok(views.html.login.login())
  }

  def authenticate = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => NotImplemented,
      user => {
        Redirect(routes.Application.index()).withSession(Security.username -> user.email)
      }
    )
  }

  def logout = Action {
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }

}
