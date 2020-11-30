import model.UserModel

object MainApp {


  def test(): String ={
    val u1 = new UserModel("0", "nameTest", "userT", "somePWD", "admin")
    u1.toString
  }
  println(test())
}
