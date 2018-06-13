package pavlomi.poloniex

import java.time.Instant

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.model.Uri.Query
import akka.stream.Materializer
import enumeratum.values.{IntEnum, IntEnumEntry, StringEnum, StringEnumEntry}
import pavlomi.poloniex.PoloniexPublicApi.Period
import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto._

import scala.concurrent.{ExecutionContext, Future}

class PoloniexPublicApi(implicit actorSystem: ActorSystem,
                        mac: Materializer,
                        ec: ExecutionContext) {

  /*
  Returns candlestick chart data.
   */
  def returnChartData(start: Instant,
                      end: Instant,
                      period: Period,
                      currencyPair: PoloniexCurrencyPair)
    : Future[PoloniexSuccessSeqResponse[ReturnChartDataResponse]] = {
    val method = PoloniexPublicApi.Method.ReturnChartData.value
    val uri = Uri(POLONIEX_PUBLIC_API_URL).withQuery(
      Query(
        "command" -> method,
        "currencyPair" -> currencyPair.toString,
        "start" -> start.getEpochSecond.toString,
        "end" -> end.getEpochSecond.toString,
        "period" -> period.value.toString
      )
    )

    val httpRequest = HttpRequest(HttpMethods.GET, uri)

    http().singleRequest(httpRequest).map(parseHttpResponse _)
  }

  private def parseHttpResponse[R <: PoloniexResponse](
      httpResponse: HttpResponse): R = ???

  private def parseSeqHttpResponse[R <: PoloniexResponse](
      httpResponse: HttpResponse): Right[PoloniexFailureResponse, Seq[R]] = ???

  private def http() = Http()

  private val POLONIEX_PUBLIC_API_URL = "https://poloniex.com" + "/public"
}

object PoloniexPublicApi {

  sealed abstract class Method(val value: String) extends StringEnumEntry
  object Method extends StringEnum[Method] {
    case object Return24Value extends Method("return24Value")
    case object ReturnTicker extends Method("returnTicker")
    case object ReturnOrderBook extends Method("returnOrderBook")
    case object ReturnTradeHistory extends Method("returnTradeHistory")
    case object ReturnChartData extends Method("returnChartData")
    case object ReturnCurrencies extends Method("returnCurrencies")
    case object ReturnLoadOrders extends Method("returnLoadOrders")

    val values = findValues
  }

  sealed abstract class Period(val value: Int) extends IntEnumEntry
  object Period extends IntEnum[Period] {
    case object s300 extends Period(300)
    case object s900 extends Period(900)
    case object s1800 extends Period(1800)
    case object s7200 extends Period(7200)
    case object s14400 extends Period(14400)
    case object s86400 extends Period(86400)

    val values = findValues
  }

}
