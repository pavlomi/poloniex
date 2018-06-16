package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnCompleteBalancesResponse(value: Map[PoloniexCurrency, ReturnCompleteBalances]) extends PoloniexSuccessResponse

case class ReturnCompleteBalances(
  available: String,
  onOrders: String,
  btcValue: String
)
