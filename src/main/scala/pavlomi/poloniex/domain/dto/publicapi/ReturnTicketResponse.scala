package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnTicketResponse(value: Map[PoloniexCurrencyPair, ReturnTicket]) extends PoloniexSuccessResponse

case class ReturnTicket(
  id: Int,
  last: String,
  lowestAsk: String,
  highestBid: String,
  percentChange: String,
  baseVolume: String,
  quoteVolume: String,
  isFrozen: String,
  high24hr: String,
  low24hr: String
)
