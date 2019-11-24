package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.OrderNumber
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

trait SellResponse extends PoloniexSuccessResponse
case class SellSuccessResponse(orderNumber: OrderNumber, resultingTrades: Seq[ResultingTrade]) extends SellResponse
case class SellErrorResponse(error: String) extends SellResponse
