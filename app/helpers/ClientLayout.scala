package helpers

object ClientLayout {

  def getClientHome(cls: String): String ={
    getButtonInputForm("name","/clients")(cls) + "<hr>" +
    getButtonInput(1,"id","/clients/")(cls) + "<hr>" +
    getButtonInputForm("version","/clients/version/")(cls)
  }

  def getButtonInputForm(label: String, link: String)(cls: String): String ={
    "<form action=" + link + " method=GET>" +
    "<input type=\"text\" name=\"" + label + "\" placeholder=\"Input " + label + "\">" +
    "<input class=\"" + cls + "\" type=\"submit\" value=\"submit\">" +
    "</form>"
  }

  def getButtonInput(id: Long, label: String, link: String)(cls: String): String ={
      "<input type=\"text\" id=\"" + label + "-" + id + "\" name=\"" + label + "\" placeholder=\"Input " + label + "\">" +
      "<input class=\"" + cls + "\" type=\"button\" " +
        "onclick=\"window.location.href='" + link + "' + document.getElementById('" + label + "-" + id + "').value\" " +
        "value=\"submit\">"
  }
}
