package pavlomi.poloniex

import java.time.Instant

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.stream.Materializer
import enumeratum.values.{StringEnum, StringEnumEntry}
import pavlomi.poloniex.domain.dto.{PoloniexFailureResponse, PoloniexResponse}
import pavlomi.poloniex.domain.{PoloniexAPIKey, PoloniexSecret}

import scala.collection.immutable.Seq
import scala.concurrent.{ExecutionContext, Future}

class PoloniexTradingApi(APIKey: PoloniexAPIKey, secret: PoloniexSecret)(implicit actorSystem: ActorSystem, mac: Materializer, ec: ExecutionContext) {

  /**
   * Returns all of your available balances.
   */
  def returnBalances(): Future[PoloniexResponse] = {
    val method   = PoloniexTradingApi.Method.ReturnBalances.value
    val formData = FormData(Map("nonce" -> getNonce.toString, "method" -> method))

    val httpRequest = HttpRequest(
      HttpMethods.POST,
      POLONIEX_TRADING_API_URL,
      getHeaders(formData),
      formData.toEntity
    )

    http().singleRequest(httpRequest).map(parseHttpResponse _)
  }

  /**
   * Returns all of your balances, including available balance, balance on orders, and the estimated BTC value of your balance.
   * By default, this call is limited to your exchange account; set the "account" POST parameter to "all" to include your margin and lending accounts.
   */
  def returnCompleteBalances() = {
    val method = PoloniexTradingApi.Method.ReturnCompleteBalances.value
    ???
  }

  /**
   * Returns all of your deposit addresses.
   */
  def returnDepositAddresses() = {
    val method = PoloniexTradingApi.Method.ReturnDepositAddresses.value
    ???
  }

  /**
   * Generates a new deposit address for the currency specified by the "currency" POST parameter.
   */
  def generateNewAddress() = {
    val method = PoloniexTradingApi.Method.GenerateNewAddress.value
    ???
  }

  /**
   * Returns your deposit and withdrawal history within a range, specified by the "start" and "end" POST parameters, both of which should be given as UNIX timestamps.
   */
  def returnDepositsWithdrawals() = {
    val method = PoloniexTradingApi.Method.ReturnDepositsWithdrawals.value
    ???
  }

  /**
   * Returns your open orders for a given market, specified by the "currencyPair" POST parameter, e.g. "BTC_XCP".
   * Set "currencyPair" to "all" to return open orders for all markets.
   */
  def returnOpenOrders() = {
    val method = PoloniexTradingApi.Method.ReturnOpenOrders.value
    ???
  }

  /**
   * Returns your trade history for a given market, specified by the "currencyPair" POST parameter.
   * You may specify "all" as the currencyPair to receive your trade history for all markets.
   * You may optionally specify a range via "start" and/or "end" POST parameters, given in UNIX timestamp format; if you do not specify a range, it will be limited to one day.
   * You may optionally limit the number of entries returned using the "limit" parameter, up to a maximum of 10,000.
   * If the "limit" parameter is not specified, no more than 500 entries will be returned.
   */
  def returnTradeHistory = {
    val method = PoloniexTradingApi.Method.ReturnTradeHistory.value
    ???
  }

  /**
   * Returns all trades involving a given order, specified by the "orderNumber" POST parameter.
   * If no trades for the order have occurred or you specify an order that does not belong to you, you will receive an error.
   */
  def returnOrderTrades = {
    val method = PoloniexTradingApi.Method.ReturnOrderTrades.value
    ???
  }

  /**
   * Places a limit buy order in a given market.
   * Required POST parameters are "currencyPair", "rate", and "amount".
   * If successful, the method will return the order number.
   */
  def buy() = {
    val method = PoloniexTradingApi.Method.Buy.value
    ???
  }

  /**
   * Places a sell order in a given market.
   * Parameters and output are the same as for the buy method.
   */
  def sell() = {
    val method = PoloniexTradingApi.Method.Sell.value
    ???
  }

  /**
   * Cancels an order you have placed in a given market.
   * Required POST parameter is "orderNumber".
   */
  def cancelOrder() = {
    val method = PoloniexTradingApi.Method.CancelOrder.value
    ???
  }

  def moveOrder = ???

  def withdraw = ???

  def returnFeeInfo = ???

  def returnAvailableAccountBalances = ???

  def returnTradableBalances = ???

  def transferBalance = ???

  def returnMarginAccountSummary = ???

  def marginBuy = ???

  def marginSell = ???

  def getMarginPosition = ???

  def closeMarginPosition = ???

  def createLoanOffer = ???

  def cancelLoanOffer = ???

  def returnOpenLoanOffers = ???

  def returnActiveLoans = ???

  def returnLendingHistory = ???

  def toggleAutoRenew = ???

  private def parseHttpResponse[R <: PoloniexResponse](httpResponse: HttpResponse): R = ???

  private def parseSeqHttpResponse[R <: PoloniexResponse](httpResponse: HttpResponse): Right[PoloniexFailureResponse, Seq[R]] = ???

  private def getNonce = Instant.now.getEpochSecond * 100

  private def getHeaders(formData: FormData): Seq[HttpHeader] = Seq(
    RawHeader("Sign", sign(formData.fields.toString)),
    RawHeader("Key", APIKey.value)
  )

  private def http() = Http()

  private def sign(postData: String): String = ???

  private val POLONIEX_TRADING_API_URL = "https://poloniex.com" + "/tradingApi"
}

object PoloniexTradingApi {

  sealed abstract class Method(val value: String) extends StringEnumEntry
  object Method extends StringEnum[Method] {
    case object ReturnBalances                 extends Method("returnBalances")
    case object ReturnCompleteBalances         extends Method("returnCompleteBalances ")
    case object ReturnDepositAddresses         extends Method("returnDepositAddresses")
    case object GenerateNewAddress             extends Method("generateNewAddress")
    case object ReturnDepositsWithdrawals      extends Method("returnDepositsWithdrawals")
    case object ReturnOpenOrders               extends Method("returnOpenOrders")
    case object ReturnTradeHistory             extends Method("returnTradeHistory")
    case object ReturnOrderTrades              extends Method("returnOrderTrades")
    case object Buy                            extends Method("buy")
    case object Sell                           extends Method("sell")
    case object CancelOrder                    extends Method("cancelOrder")
    case object MoveOrder                      extends Method("moveOrder")
    case object Withdraw                       extends Method("withdraw")
    case object ReturnFeeInfo                  extends Method("ReturnFeeInfo")
    case object ReturnAvailableAccountBalances extends Method("returnAvailableAccountBalances")
    case object ReturnTradableBalances         extends Method("returnTradableBalances")
    case object TransferBalance                extends Method("transferBalance")
    case object ReturnMarginAccountSummary     extends Method("returnMarginAccountSummary")
    case object MarginBuy                      extends Method("marginBuy")
    case object MarginSell                     extends Method("marginSell")
    case object GetMarginPosition              extends Method("getMarginPosition")
    case object CloseMarginPosition            extends Method("closeMarginPosition")
    case object CreateLoanOffer                extends Method("createLoanOffer")
    case object CancelLoanOffer                extends Method("cancelLoanOffer")
    case object ReturnOpenLoanOffer            extends Method("returnOpenLoanOffer")
    case object ReturnActiveLoans              extends Method("returnActiveLoans")
    case object ReturnLendingHistory           extends Method("returnLendingHistory")
    case object ToggleAutoRenew                extends Method("toggleAutoRenew")

    val values = findValues
  }

}
