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

  /**
   * Cancels an order and places a new one of the same type in a single atomic transaction, meaning either both operations will succeed or both will fail.
   */
  def moveOrder = {
    val method = PoloniexTradingApi.Method.MoveOrder.value
    ???
  }

  /**
   * Immediately places a withdrawal for a given currency, with no email confirmation.
   * In order to use this method, the withdrawal privilege must be enabled for your API key
   */
  def withdraw = {
    val method = PoloniexTradingApi.Method.Withdraw.value
    ???
  }

  /**
   * If you are enrolled in the maker-taker fee schedule, returns your current trading fees and trailing 30-day volume in BTC.
   * This information is updated once every 24 hours.
   */
  def returnFeeInfo = {
    val method = PoloniexTradingApi.Method.ReturnFeeInfo.value
    ???
  }

  /**
   * Returns your balances sorted by account.
   * You may optionally specify the "account" POST parameter if you wish to fetch only the balances of one account.
   */
  def returnAvailableAccountBalances = {
    val method = PoloniexTradingApi.Method.ReturnAvailableAccountBalances.value
    ???
  }

  /**
   * Returns your current tradable balances for each currency in each market for which margin trading is enabled.
   * Please note that these balances may vary continually with market conditions.
   */
  def returnTradableBalances = {
    val method = PoloniexTradingApi.Method.ReturnTradableBalances.value
    ???
  }

  /**
   * Transfers funds from one account to another (e.g. from your exchange account to your margin account).
   * Required POST parameters are "currency", "amount", "fromAccount", and "toAccount"
   */
  def transferBalance = {
    val method = PoloniexTradingApi.Method.TransferBalance.value
    ???
  }

  /**
   * Returns a summary of your entire margin account.
   * This is the same information you will find in the Margin Account section of the Margin Trading page, under the Markets list.
   */
  def returnMarginAccountSummary = {
    val method = PoloniexTradingApi.Method.ReturnMarginAccountSummary.value
    ???
  }

  /**
   * Places a margin buy order in a given market.
   * Required POST parameters are "currencyPair", "rate", and "amount".
   * You may optionally specify a maximum lending rate using the "lendingRate" parameter.
   * If successful, the method will return the order number and any trades immediately resulting from your order
   */
  def marginBuy = {
    val method = PoloniexTradingApi.Method.MarginBuy.value
    ???
  }

  /**
   * Places a margin sell order in a given market. Parameters and output are the same as for the marginBuy method.
   */
  def marginSell = {
    val method = PoloniexTradingApi.Method.MarginSell.value
    ???
  }

  /**
   * Returns information about your margin position in a given market, specified by the "currencyPair" POST parameter.
   * You may set "currencyPair" to "all" if you wish to fetch all of your margin positions at once.
   * If you have no margin position in the specified market, "type" will be set to "none". "liquidationPrice" is an estimate, and does not necessarily represent the price at which an actual forced liquidation will occur.
   * If you have no liquidation price, the value will be -1.
   */
  def getMarginPosition = {
    val method = PoloniexTradingApi.Method.GetMarginPosition.value
    ???
  }

  /**
   * Closes your margin position in a given market (specified by the "currencyPair" POST parameter) using a market order.
   * This call will also return success if you do not have an open position in the specified market.
   */
  def closeMarginPosition = {
    val method = PoloniexTradingApi.Method.CloseMarginPosition.value
    ???
  }

  /**
   * Creates a loan offer for a given currency.
   * Required POST parameters are "currency", "amount", "duration", "autoRenew" (0 or 1), and "lendingRate".
   */
  def createLoanOffer = {
    val method = PoloniexTradingApi.Method.CreateLoanOffer.value
    ???
  }

  /**
   * Cancels a loan offer specified by the "orderNumber" POST parameter.
   */
  def cancelLoanOffer = {
    val method = PoloniexTradingApi.Method.CancelLoanOffer.value
    ???
  }

  /**
   * Returns your open loan offers for each currency.
   */
  def returnOpenLoanOffers = {
    val method = PoloniexTradingApi.Method.ReturnOpenLoanOffers.value
    ???
  }

  /**
   * Returns your active loans for each currency.
   */
  def returnActiveLoans = {
    val method = PoloniexTradingApi.Method.ReturnActiveLoans.value
    ???
  }

  /**
   * Returns your lending history within a time range specified by the "start" and "end" POST parameters as UNIX timestamps.
   * "limit" may also be specified to limit the number of rows returned.
   */
  def returnLendingHistory = {
    val method = PoloniexTradingApi.Method.ReturnLendingHistory.value
    ???
  }

  /**
   * Toggles the autoRenew setting on an active loan, specified by the "orderNumber" POST parameter.
   * If successful, "message" will indicate the new autoRenew setting.
   */
  def toggleAutoRenew = {
    val method = PoloniexTradingApi.Method.ToggleAutoRenew.value
    ???
  }

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
    case object ReturnOpenLoanOffers           extends Method("returnOpenLoanOffers")
    case object ReturnActiveLoans              extends Method("returnActiveLoans")
    case object ReturnLendingHistory           extends Method("returnLendingHistory")
    case object ToggleAutoRenew                extends Method("toggleAutoRenew")

    val values = findValues
  }

}
