package controllers

import play.api.mvc._

import play.api._

class Clients extends Controller{


  def list = Action {
    Ok("show clients")
  }

  def greet(name: String) = Action {
    Ok(s"hello $name")
  }

  def show(id: Long) = Action {
    Ok(s"you typed in that you wanted to view <strong>$id<strong>")
  }

  def showString(id: String) = Action {
    Ok(s"same as before $id")
  }
}
