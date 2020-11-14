package http

import java.util.UUID

import scala.concurrent.{ExecutionContext, Future}


trait ResultRepository {
  def all(): Future[Seq[Int]]
  def post(createAddressBook:Result): Future[Result]

}

class InMemoryResultRepository(todo:Seq[Int] = Seq.empty)(implicit  ex:ExecutionContext) extends ResultRepository {
  private var results: Vector[Int] = todo.toVector

  override def all(): Future[Seq[Int]] = Future.successful(results)

  override def post(value: Result): Future[Result] = {
    Future.successful {
      val res = value.res.toInt
        results = results :+ res
      value
    }
  }
}
