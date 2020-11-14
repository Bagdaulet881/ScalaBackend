package http

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

object HttpServerSample {

  def main(args: Array[String]): Unit = {


    val rootBehavior = Behaviors.setup[Nothing] { context =>
      val results:Seq[Int] = Seq(
      )
      val resRepository = new InMemoryResultRepository(results)(context.executionContext)
      val router = new MyRouter(resRepository)(context.system, context.executionContext)
      MyServer.startHttpServer(router.route)(context.system, context.executionContext)
      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
  }


}
