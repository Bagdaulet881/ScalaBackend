package http

import java.util.UUID

import akka.actor.typed.ActorSystem
import akka.pattern.StatusReply
import com.typesafe.config.ConfigFactory

import scala.concurrent.{ExecutionContext, Future}
import model.ShopCart
import sample.persistence.ShoppingCart

import scala.concurrent.{ExecutionContext, Future}


trait ResultRepository {
  def all(): Future[Seq[ShopCart]]
  def post(createAddressBook:ShopCart): Future[ShopCart]
}

class InMemoryResultRepository(todo:Seq[ShopCart] = Seq.empty)(implicit  ex:ExecutionContext) extends ResultRepository {
  private var results: Vector[ShopCart] = todo.toVector

  private var counter = 0
  def newCartId(): String = {
    counter += 1
    s"cart-$counter"
  }


  override def all(): Future[Seq[ShopCart]] = Future.successful(results)

  override def post(value: ShopCart): Future[ShopCart] = {
    Future.successful {
      results = results :+ value
      value
    }
    ???
//    val cart = (ShoppingCart(newCartId()))
//    cart ! ShoppingCart.AddItem("foo", 42, probe.ref)
//    (StatusReply.Success(ShoppingCart.Summary(Map("foo" -> 42), checkedOut = false)))
  }
}
