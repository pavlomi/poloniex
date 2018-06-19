package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnDepositAddressesResponse(value: Map[PoloniexCurrency, String]) extends PoloniexSuccessResponse
