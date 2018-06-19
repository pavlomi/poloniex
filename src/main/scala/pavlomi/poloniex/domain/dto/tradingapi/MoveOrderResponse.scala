package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class MoveOrderResponse(success: Long, orderNumber: String, resultingTrades: Map[PoloniexCurrencyPair, Seq[String]]) extends PoloniexSuccessResponse
