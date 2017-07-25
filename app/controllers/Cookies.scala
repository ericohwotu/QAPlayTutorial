package controllers

import play.api.mvc._
import play.twirl.api.Html

class Cookies extends Controller {

  val script: String = "<script>window.alert(\"hello for the first time\")</script>"
  val view: Html = views.html.getcontent.render()

  def index: Action[AnyContent] = Action {
    Ok(views.html.result("cookies example",view)("good")).withCookies(
      (Cookie("theme", "error"))
    )
  }

  def clearTheme: Action[AnyContent] = Action { request: Request[AnyContent] =>
    //val theme = request.cookies.get("theme")get.value
    val theme = request.cookies.get("theme").getOrElse(Cookie("theme","good")).value

    Ok(views.html.result("cookie changed " + theme, view)(theme))
      .discardingCookies(DiscardingCookie("theme"))
  }

  def addSession: Action[AnyContent] = Action { request: Request[AnyContent] =>
    request.session.data.get("action").getOrElse("alert") match {
      case "alert" => Ok (views.html.result ("Setting Session", view) ("good",script) )
                       .withSession ("connected" -> "mynewspace@outlook.com", "theme" -> "warning",
                        "action" -> "add")

      case "add" => Ok(views.html.result ("Adding extra Session", view) ("warning")).withSession(
        request.session + ("name"->"Eric") + ("action"->"clear")
      )

      case "clear" => Ok(views.html.result ("Clearing Session", view) ("error") ).withNewSession
    }
  }

  def flashSession: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
     Redirect("/home").flashing("isSet" -> "true", "success" -> "hey are you really this dumb")
  }

  def flashRedirect: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok {
      views.html.result(request.flash.get("success").getOrElse("Welcome!"), views.html.getcontent.render())("good")
    }
  }

}
