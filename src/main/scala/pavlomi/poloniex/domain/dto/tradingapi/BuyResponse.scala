package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class BuyResponse(orderNumber: Long, resultingTrades: Seq[ResultingTrade]) extends PoloniexSuccessResponse

case class ResultingTrade(
  amount: String,
  date: String,
  rate: String,
  total: String,
  tradeID: String,
  `type`: String
)
