package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  val num = 488

  val ok = Ok("Hello World")
  val notFound = NotFound
  val pageNotFound = NotFound("<h1>Page Not Found</h1>").as("text/html")
  val badRequest = BadRequest("Error Happened")
  val oops = InternalServerError("<h1>Oops</h1>").as("text/html")
  val anyStatus = Status(num)("strange response type")


  def index: Action[AnyContent] = Action {

    Ok(views.html.result("Index",views.html.getcontent.render())("good"))
  }

  def redirect: Action[AnyContent] = Action {
    Redirect("http://www.Facebook.com")
  }

  def reverse: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.Application.index())
  }

  def notImplemented: Action[AnyContent] = TODO
}