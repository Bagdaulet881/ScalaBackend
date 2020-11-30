import model.Book

object Test extends App {
  def f(): Unit ={


    val b1 = new Book("id","asd58","nametest", "asdasd")
    val b3 = new Book("id2","asd58","nametest2", "asdasd")
    val b2 = new Book("id3","asd58","nametest2", "asdasd")


    val colors = Map("1" -> b1, "2" -> b2, "3"->b3)
    val default = (-1,"")
    val value = "nametest2"
    val ids = colors.find(_._2.name==value).getOrElse(default)._1
    println(ids)
  }
  f()
}
