package api

import akka.actor.typed.scaladsl.AskPattern._
import akka.actor.typed.{ActorRef, ActorSystem, Scheduler}
import akka.http.scaladsl.server.{Directives, Route}
import akka.util.Timeout
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import model.ShopCart
import sample.persistence.ShoppingCart

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContext, Future}
case class PostText(text: String)
trait Router {
  def route: Route
}

class NodeRouter(node:ActorRef[ShoppingCart.Command])(implicit system: ActorSystem[_], ex:ExecutionContext)
  extends  Router
    with  Directives {
  implicit val timeout: Timeout = 5.seconds
  implicit val scheduler: Scheduler = system.scheduler
  override def route: Route =
    concat(
      pathPrefix("process") {
        path(IntNumber) { n =>
          get {
            val processFuture: Future[ShoppingCart.Summary] = node.ask(ref => ShoppingCart.AddItem("asdId",n, ref))(timeout,scheduler).mapTo[ShoppingCart.Summary]
            onSuccess(processFuture){response =>
              complete(response)
            }
          }
        }
      }
    )
}




