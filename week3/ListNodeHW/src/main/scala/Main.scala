import scala.annotation.tailrec

object Main extends App{
  var justList = List(1,2,3,4,5,6,7)
  var x = 5

  @tailrec def search(x: Int, tail:List[Int]): String ={
    if(tail.length == 0){
      "Not Found!"
    }else {
      var head = tail.head
      if(head == x){
        x + " found!"
      }else{
        search(x, tail.tail)
      }
    }

  }
  println(search(x, justList))

}
