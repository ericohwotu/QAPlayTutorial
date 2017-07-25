package controllers

import play.api.mvc._

class Cookies extends Controller {

  val script: String = "<script>window.alert(\"hello for the first time\")</script>"
  def index: Action[AnyContent] = Action {
    Ok(views.html.result("cookies example")("good")).withCookies(
      (Cookie("theme", "error"))
    )
  }

  def clearTheme: Action[AnyContent] = Action { request: Request[AnyContent] =>
    //val theme = request.cookies.get("theme")get.value
    val theme = request.cookies.get("theme")

    val th = !theme.isEmpty match {
      case true => theme.get.value
      case false => "good"
    }

    Ok(views.html.result("cookies discarded: " + th)(th))
      .discardingCookies(DiscardingCookie("theme"))
  }

  def addSession: Action[AnyContent] = Action { request: Request[AnyContent] =>
    request.session.data.get("action").getOrElse("alert") match {
      case "alert" => Ok (views.html.result ("Setting Session") ("good",script) )
                       .withSession ("connected" -> "mynewspace@outlook.com", "theme" -> "warning",
                        "action" -> "add")
      case "add" => Ok(views.html.result ("Adding extra Session") ("warning")).withSession(
        request.session + ("name"->"Eric") + ("action"->"clear")
      )
      case "clear" => Ok(views.html.result ("Clearing Session") ("error") ).withNewSession
    }
  }

  def flashSession: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
     Redirect("/home").flashing("isSet" -> "true", "success" -> "hey are you really this dumb")
  }

  def flashRedirect: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok {
      request.flash.get("success").getOrElse("Welcome!")
    }
  }

}
