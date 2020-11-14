package http

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, Multipart}
import akka.http.scaladsl.server.{Directives, Route}
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}
trait  Router {
  def route:Route
}
class MyRouter(val todoRepository: ResultRepository)(implicit system: ActorSystem[_],  ex:ExecutionContext) extends  Router with  Directives{

  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

//  implicit val logger: Logger = LoggerFactory.getLogger(getClass)

  override def route = {
    concat(
      path("ping") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "pong2"))
        }
      },
      pathPrefix("result") {
        concat(
          pathEndOrSingleSlash {
            concat(
              get {
                complete(todoRepository.all())
              },
              post {
                entity(as[Result]) { value=>
                  todoRepository.post(value)
                  complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "result POSTed"))
                }
                }
            )
              }
            )
          },

        )
      }
}

