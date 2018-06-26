package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnAvailableAccountBalances(
  exchange: Map[PoloniexCurrency, String],
  margin: Option[Map[PoloniexCurrency, String]],
  lending: Option[Map[PoloniexCurrency, String]]
) extends PoloniexSuccessResponse
