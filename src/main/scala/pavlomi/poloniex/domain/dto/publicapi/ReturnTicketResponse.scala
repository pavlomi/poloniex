package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnTicketResponse(value: Map[PoloniexCurrencyPair, ReturnTicket]) extends PoloniexSuccessResponse

case class ReturnTicket(
  id: Int,
  last: BigDecimal,
  lowestAsk: BigDecimal,
  highestBid: BigDecimal,
  percentChange: BigDecimal,
  baseVolume: String,
  quoteVolume: String,
  isFrozen: String,
  high24hr: String,
  low24hr: String
)
