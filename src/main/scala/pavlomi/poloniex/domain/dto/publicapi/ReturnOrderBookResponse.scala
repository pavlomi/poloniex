package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

trait OrderBookResponse extends PoloniexSuccessResponse

case class ReturnOrderBookResponse(value: Map[PoloniexCurrencyPair, ReturnOrderBook]) extends OrderBookResponse

case class ReturnOrderBook(
  asks: Seq[(String, BigDecimal)],
  bids: Seq[(String, BigDecimal)],
  isFrozen: String,
  seq: Long
) extends OrderBookResponse
