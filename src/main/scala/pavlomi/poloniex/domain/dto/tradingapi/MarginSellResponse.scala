package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class MarginSellResponse(
  success: Int,
  message: String,
  orderNumber: String,
  resultingTrades: Map[PoloniexCurrencyPair, Seq[Trade]]
) extends PoloniexSuccessResponse
