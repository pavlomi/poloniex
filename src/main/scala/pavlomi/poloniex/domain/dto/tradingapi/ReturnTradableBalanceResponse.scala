package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.{PoloniexCurrency, PoloniexCurrencyPair}
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnTradableBalanceResponse(value: Map[PoloniexCurrencyPair, Map[PoloniexCurrency, String]]) extends PoloniexSuccessResponse
