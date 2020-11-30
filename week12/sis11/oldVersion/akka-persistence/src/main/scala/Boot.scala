import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.{Behaviors, Routers}
import api.Server
import api.NodeRouter
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.{Logger, LoggerFactory}
import sample.persistence.ShoppingCart

object Boot {
  val config: Config = ConfigFactory.load()
  val address = config.getString("http.ip")
  val port = config.getInt("http.port")
  val nodeId = config.getString("clustering.ip")

  def main(args: Array[String]): Unit = {

    implicit val log: Logger = LoggerFactory.getLogger(getClass)

    val rootBehavior = Behaviors.setup[ShoppingCart.Command] { context =>
      context.spawnAnonymous(ShoppingCart("someId"))
      val group = Routers.group(ShoppingCart.NodeServiceKey)
      val node = context.spawnAnonymous(group)
      val router = new NodeRouter(node)(context.system, context.executionContext)
      Server.startHttpServer(router.route, address, port)(context.system, context.executionContext)
      Behaviors.empty
    }
    ActorSystem[ShoppingCart.Command](rootBehavior, "cluster-playground")

//    val text = List("this is a test", "of some very naive word counting", "but what can you say", "it is what it is")
//    receptionist ! JobRequest("the first job", (1 to 100000).flatMap(i => text ++ text).toList)
//    system.actorOf(Props(new ClusterDomainEventListener), "cluster-listener")

  }



}
