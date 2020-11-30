package http

import akka.actor.typed.{ActorSystem, Props}
import akka.actor.typed.scaladsl.Behaviors
import model.ShopCart
import sample.persistence.ShoppingCart
import sample.persistence.ShoppingCart.AddItem

//object HttpServerSample {
//
//  def main(args: Array[String]): Unit = {
//
//
//    val rootBehavior = Behaviors.setup[Nothing] { context =>
//      val results:Seq[ShopCart] = Seq(
//      )
//      val resRepository = new InMemoryResultRepository(results)(context.executionContext)
//      val router = new MyRouter(resRepository)(context.system, context.executionContext)
//      MyServer.startHttpServer(router.route)(context.system, context.executionContext)
//      Behaviors.empty
//    }
//    val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
//  }
//
//
//
//}

//object ExampleProcessor extends App {
//  case class Increment(value: Int)
//  import com.typesafe.config.ConfigFactory
////  val system = ActorSystem("example")
////  val system = ActorSystem[Nothing](ShoppingCart("asdasd"), "HelloAkkaHttpServer1")
////val system: ActorSystem[ShoppingCart.Command] = ActorSystem(ShoppingCart("asdasd"), "AkkaQuickStart")
////  val actorA = system.actorOf(Props(new ExampleProcessor("a")))
//  import akka.actor._
////  val system = ActorSystem("example", ConfigFactory.load("example"))
////  val actorA = system.actorOf(Props(new ShoppingCart("a")))
//
////  actorA ! Increment(2)
//
//}