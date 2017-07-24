package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {

  val num = 488

  val ok = Ok("Hello World")
  val notFound = NotFound
  val pageNotFound = NotFound("<h1>Page Not Found</h1>")
  val badRequest = BadRequest("Error Happened")
  val oops = InternalServerError("Oops")
  val anyStatus = Status(num)("strange response type")


  def index = Action {
    ok
  }

  def redirect = Action {
      Redirect("http://www.Facebook.com")
  }

  def reverse = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.Application.index())
  }

  def notImplemented = TODO
}