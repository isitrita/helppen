package controllers

import model.Users
import model.card.{Card, Cards}
import play.api.mvc._

object Application extends Controller with Secured {

  def index = withAuth { username => implicit request =>
    val cards: Seq[Card] = Users.findOneByUsername(username).flatMap(u => Cards.allBy(u)).getOrElse(Seq.empty)

    val links = views.html.main.medium_links()
    val scripts = views.html.main.medium_scripts()
    val content = views.html.main.medium(cards)
    Ok(views.html.index(links, scripts, content))
  }

}