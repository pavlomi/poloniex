package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.OrderNumber
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

trait BuyResponse extends PoloniexSuccessResponse

case class BuyErrorResponse(error: String) extends BuyResponse
case class BuySuccessResponse(orderNumber: OrderNumber, resultingTrades: Seq[ResultingTrade]) extends BuyResponse

case class ResultingTrade(
  amount: String,
  date: String,
  rate: String,
  total: String,
  tradeID: String,
  `type`: String
)
