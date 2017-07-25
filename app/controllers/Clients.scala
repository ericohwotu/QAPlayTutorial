package controllers

import helpers.ClientLayout
import play.api.mvc._
import play.twirl.api.Html

class Clients extends Controller{
  val form = (x: String) => ClientLayout.getClientHome(x)
  val theme: Html = Html.apply(form("good"))
  val num = 448
  val view: Html = views.html.getcontent.render()
  val ok = Ok(views.html.result("Hello World", view)("good"))
  val notFound = NotFound
  val pageNotFound = NotFound(views.html.result("Page Not Found", view)("notfound"))
  val badRequest = BadRequest(views.html.result("There was an error", view)("error"))
  val oops = InternalServerError(views.html.result("Oops Something Went Wrong", view)("error"))
  val anyStatus = Status(num)("strange response type")


  def index: Action[AnyContent] = Action {

    Ok(views.html.result("Clients", theme)("good"))
  }
  def list:Action[AnyContent]= Action {
    Ok(views.html.result("Show Clients",theme)("good"))
  }

  def greet(name: String): Action[AnyContent] = Action {
    Ok(views.html.result("Hello " + name,theme)("good"))
  }

  def show(id: Long): Action[AnyContent] = Action {
    Ok(views.html.result("You wanted to view " + id, theme)("good"))
  }

  def showString(id: String): Action[AnyContent] = Action {
    Ok(s"same as before $id")
  }

  def optionalHandling(version: Option[String]): Action[AnyContent]= Action {
    Ok(views.html.result(version.getOrElse("error"), theme)("good"))
  }

  def resultHelpers(version: Option[String]): Action[AnyContent]= Action {
    hFunc(version)
  }

  def hFunc(version: Option[String]): Result={
    version.getOrElse("bad") match {
      case "bad" => badRequest
      case "good" => ok
      case "notfound" => pageNotFound
      case "oops" => oops
      case _ => anyStatus
    }
  }
}
