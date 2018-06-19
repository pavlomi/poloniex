package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class SellResponse(orderNumber: Long, resultingTrades: Seq[ResultingTrade]) extends PoloniexSuccessResponse
