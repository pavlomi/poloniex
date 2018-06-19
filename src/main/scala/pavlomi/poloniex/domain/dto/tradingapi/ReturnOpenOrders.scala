package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse
trait ReturnOpenOrdersResponse extends PoloniexSuccessResponse

case class ReturnOpenOrdersSingle(value: Seq[ReturnOpenOrders]) extends ReturnOpenOrdersResponse

case class ReturnOpenOrdersAll(value: Map[PoloniexCurrencyPair, Seq[ReturnOpenOrders]]) extends ReturnOpenOrdersResponse

case class ReturnOpenOrders(
  orderNumber: Long,
  `type`: String,
  rate: String,
  amount: String,
  total: String
)
