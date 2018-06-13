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
