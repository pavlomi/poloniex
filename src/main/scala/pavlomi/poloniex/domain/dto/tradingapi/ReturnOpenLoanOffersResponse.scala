package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnOpenLoanOffersResponse(value: Map[PoloniexCurrency, Seq[OpenLoadOffer]]) extends PoloniexSuccessResponse

case class OpenLoadOffer(id: BigInt, rate: String, amount: String, duration: String, autoRenew: Int, date: String)
