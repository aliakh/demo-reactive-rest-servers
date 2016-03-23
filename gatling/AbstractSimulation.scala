import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._

abstract class AbstractSimulation extends Simulation {

  val rampUpTimeSecs = 60 seconds
  val testTimeSecs = 90 seconds
  val noOfUsers = 500
  val minWaitMSecs = 1000 milliseconds
  val maxWaitMSecs = 3000 milliseconds

  def baseName: String
  val requestName = baseName + "-request"
  val scenarioName = baseName + "-scenario"
  def URI: String

  val baseURL = "http://localhost:9090"
  val delayMSecs = 1000

  val httpHeaders = Map(
    "Accept-Encoding" -> "gzip,deflate",
    "Content-Type" -> "text/json;charset=UTF-8",
    "Keep-Alive" -> "120"
  )

  val scn = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI)
          .headers(httpHeaders)
          .check(status.is(200))
      )
        .pause(minWaitMSecs, maxWaitMSecs)
    }

  val httpConf = http.baseURL(baseURL)
  setUp(scn.inject(rampUsers(noOfUsers) over rampUpTimeSecs).protocols(httpConf))

}