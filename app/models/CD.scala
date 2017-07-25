package models

import play.api.data._
import play.api.data.Forms._

case class CD(name: String, genre: String)

object CD{
  val createCDForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "genre" -> nonEmptyText
    )(CD.apply _)(CD.unapply _)
  )

  var cds = Seq(
    CD("jump", "mydream"),
    CD("jump 1", "mydream"),
    CD("jump 2", "mydream"),
    CD("jump 3", "mydream"),
    CD("jump 4", "mydream"),
    CD("jump 5", "mydream"),
    CD("jump 6", "mydream")
  )
}
