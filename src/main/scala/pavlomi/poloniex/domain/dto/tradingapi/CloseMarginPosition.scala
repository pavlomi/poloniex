package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class CloseMarginPositionResponse(success: Int, message: String, resultingTrades: Map[PoloniexCurrencyPair, Seq[Trade]]) extends PoloniexSuccessResponse
