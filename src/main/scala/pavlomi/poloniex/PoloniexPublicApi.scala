package pavlomi.poloniex

import java.time.Instant

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.Uri.Query
import akka.stream.Materializer
import enumeratum.values.{IntEnum, IntEnumEntry, StringEnum, StringEnumEntry}
import pavlomi.poloniex.PoloniexPublicApi.Period
import pavlomi.poloniex.domain.{PoloniexCurrency, PoloniexCurrencyPair}
import pavlomi.poloniex.domain.dto._
import pavlomi.poloniex.domain.dto.publicapi._
import spray.json._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class PoloniexPublicApi(implicit actorSystem: ActorSystem, mac: Materializer, ec: ExecutionContext) {
  import JsonConversion._

  type PoloniexResponseFut[S <: PoloniexSuccessResponse] = Future[Either[PoloniexErrorResponse, S]]
  type PoloniexSeqResponseFut[T]                         = PoloniexResponseFut[PoloniexSuccessSeqResponse[T]]

  /**
   * Returns the ticker for all markets.
   *
   * Call: https://poloniex.com/public?command=returnTicker
   */
  def returnTicket(): PoloniexResponseFut[ReturnTicketResponse] = {
    val command = PoloniexPublicApi.Method.ReturnTicker.value
    val query  = Query("command" -> command)
    httpRequestRun(query) { strict =>
      val response = strict.data.utf8String.parseJson.convertTo[Map[PoloniexCurrencyPair, ReturnTicket]]
      ReturnTicketResponse(response)
    }
  }

  /**
   * Returns the 24-hour volume for all markets, plus totals for primary currencies.
   *
   * Call: https://poloniex.com/public?command=return24hVolume
   */
  def return24Volume(): PoloniexResponseFut[Return24VolumeResponse] = {
    val command = PoloniexPublicApi.Method.Return24hValue.value
    val query  = Query("command" -> command)
    httpRequestRun(query) { strict =>
      //TODO: to be continue implement
      val response = strict.data.utf8String
      Return24VolumeResponse(response)
    }
  }

  /**
   * Returns the order book for a given market, as well as a sequence number for use with the Push API and an indicator specifying whether the market is frozen.
   * You may set currencyPair to "all" to get the order books of all markets.
   *
   * Call: https://poloniex.com/public?command=returnOrderBook&currencyPair=BTC_NXT&depth=10
   */
  def returnOrderBook(currencyPair: Option[PoloniexCurrencyPair] = None, depth: Long = 10): PoloniexResponseFut[OrderBookResponse] = {
    val command                = PoloniexPublicApi.Method.ReturnOrderBook.value
    val currencyPairParameter = currencyPair.map(_.toString).getOrElse("all")
    val query                 = Query("command" -> command, currencyPairParameter -> currencyPairParameter, "depth" -> depth.toString)

    httpRequestRun(query) { strict =>
      currencyPair
        .map(_ => strict.data.utf8String.parseJson.convertTo[ReturnOrderBook])
        .getOrElse(ReturnOrderBookResponse(strict.data.utf8String.parseJson.convertTo[Map[PoloniexCurrencyPair, ReturnOrderBook]]))
    }
  }

  /**
   * Returns the past 200 trades for a given market, or up to 50,000 trades between a range specified in UNIX timestamps by the "start" and "end" GET parameters
   *
   * Call: https://poloniex.com/public?command=returnTradeHistory&currencyPair=BTC_NXT&start=1410158341&end=1410499372
   */
  def returnTradeHistory(currencyPair: PoloniexCurrencyPair, start: Instant, end: Instant): PoloniexSeqResponseFut[ReturnTradeHistory] = {
    val command         = PoloniexPublicApi.Method.ReturnTradeHistory.value
    val startTimestamp = instantTimestampToString(start)
    val endTimestamp   = instantTimestampToString(end)

    val query = Query("command" -> command, currencyPairParameter -> currencyPair.toString, "start" -> startTimestamp, "end" -> endTimestamp)
    httpRequestRun(query) { strict =>
      val tradeHistories = strict.data.utf8String.parseJson.convertTo[Seq[ReturnTradeHistory]]
      PoloniexSuccessSeqResponse(tradeHistories)
    }
  }

  /**
   * Returns candlestick chart data.
   *
   * Call: https://poloniex.com/public?command=returnChartData&currencyPair=BTC_XMR&start=1405699200&end=9999999999&period=14400
   */
  def returnChartData(currencyPair: PoloniexCurrencyPair,
                      start: Instant,
                      end: Instant,
                      period: Period = Period.s14400): PoloniexSeqResponseFut[ReturnChartDataResponse] = {
    val command = PoloniexPublicApi.Method.ReturnChartData.value
    val query = Query(
      "command"      -> command,
      "currencyPair" -> currencyPair.toString,
      "start"        -> instantTimestampToString(start),
      "end"          -> instantTimestampToString(end),
      "period"       -> period.value.toString
    )
    httpRequestRun(query) { strict =>
      val returnChartDataResponse = strict.data.utf8String.parseJson.convertTo[Seq[ReturnChartDataResponse]]
      PoloniexSuccessSeqResponse(returnChartDataResponse)
    }
  }

  /**
   * Returns information about currencies.
   *
   * Call: https://poloniex.com/public?command=returnCurrencies
   */
  def returnCurrencies(): PoloniexResponseFut[ReturnCurrenciesResponse] = {
    val command = PoloniexPublicApi.Method.ReturnCurrencies.value
    val query  = Query("command" -> command)
    httpRequestRun(query) { strict =>
      val returnCurrencies = strict.data.utf8String.parseJson.convertTo[Map[PoloniexCurrency, ReturnCurrencies]]
      ReturnCurrenciesResponse(returnCurrencies)
    }
  }

  /**
   * Returns the list of loan offers and demands for a given currency, specified by the "currency" GET parameter.
   *
   * Call: https://poloniex.com/public?command=returnLoanOrders&currency=BTC
   */
  def returnLoanOrders(currency: PoloniexCurrency): PoloniexResponseFut[ReturnLoadOrdersResponse] = {
    val command = PoloniexPublicApi.Method.ReturnLoanOrders.value
    val query  = Query("command" -> command, "currency" -> currency.value)
    httpRequestRun(query) { strict =>
      strict.data.utf8String.parseJson.convertTo[ReturnLoadOrdersResponse]
    }
  }

  private def parseHttpResponse[S <: PoloniexSuccessResponse](httpResponse: HttpResponse, f: HttpEntity.Strict => S): PoloniexResponseFut[S] =
    httpResponse match {
      case HttpResponse(status, _, entity, _) if status.isSuccess() => entity.toStrict(timeout).map(s => Right(f(s)))
      case HttpResponse(_, _, entity, _) =>
        entity.toStrict(timeout).map(s => Left(s.data.utf8String.parseJson.convertTo[PoloniexErrorResponse]))
    }

  private def httpRequestRun[S <: PoloniexSuccessResponse](query: Query)(f: HttpEntity.Strict => S): PoloniexResponseFut[S] = {
    val uri         = Uri(POLONIEX_PUBLIC_API_URL).withQuery(query)
    val httpRequest = HttpRequest(HttpMethods.GET, uri)
    http().singleRequest(httpRequest).flatMap(parseHttpResponse[S](_, f))
  }

  private def instantTimestampToString(instant: Instant) = instant.toEpochMilli.toString.substring(0, 10)

  private def http() = Http()

  private val POLONIEX_PUBLIC_API_URL = "https://poloniex.com" + "/public"
  private val currencyPairParameter   = "currencyPair"
  private val timeout                 = 3000.millis
}

object PoloniexPublicApi {

  sealed abstract class Method(val value: String) extends StringEnumEntry
  object Method extends StringEnum[Method] {
    case object Return24hValue     extends Method("return24hVolume")
    case object ReturnTicker       extends Method("returnTicker")
    case object ReturnOrderBook    extends Method("returnOrderBook")
    case object ReturnTradeHistory extends Method("returnTradeHistory")
    case object ReturnChartData    extends Method("returnChartData")
    case object ReturnCurrencies   extends Method("returnCurrencies")
    case object ReturnLoanOrders   extends Method("returnLoanOrders")

    val values = findValues
  }

  sealed abstract class Period(val value: Int) extends IntEnumEntry
  object Period extends IntEnum[Period] {
    case object s300   extends Period(300)
    case object s900   extends Period(900)
    case object s1800  extends Period(1800)
    case object s7200  extends Period(7200)
    case object s14400 extends Period(14400)
    case object s86400 extends Period(86400)

    val values = findValues
  }

}
