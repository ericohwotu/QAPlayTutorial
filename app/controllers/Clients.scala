package controllers

import play.api.mvc._

import play.api._

class Clients extends Controller{

  val num = 448
  val ok = Ok("Hello World")
  val notFound = NotFound
  val pageNotFound = NotFound("<h1>Page Not Found</h1>").as("text/html")
  val badRequest = BadRequest(views.html.result("There was an error")("error"))
  val oops = InternalServerError("<h1>Oops</h1>").as("text/html")
  val anyStatus = Status(num)("strange response type")

  def list:Action[AnyContent]= Action {
    Ok("show clients")
  }

  def greet(name: String): Action[AnyContent] = Action {
    Ok(s"hello $name")
  }

  def show(id: Long): Action[AnyContent] = Action {
    Ok(s"you typed in that you wanted to view <strong>$id<strong>").as("text/html")
  }

  def showString(id: String): Action[AnyContent] = Action {
    Ok(s"same as before $id")
  }

  def optionalHandling(version: Option[String]): Action[AnyContent]= Action {
    Ok(version.getOrElse("error"))
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
