package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class MarginBuyResponse(
  success: Int,
  message: String,
  orderNumber: String,
  resultingTrades: Map[PoloniexCurrencyPair, Seq[Trade]]
) extends PoloniexSuccessResponse

case class Trade(amount: String, date: String, rate: String, total: String, tradeID: String, `type`: String)
