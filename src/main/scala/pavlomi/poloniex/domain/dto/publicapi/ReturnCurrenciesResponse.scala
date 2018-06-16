package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnCurrenciesResponse(value: Map[PoloniexCurrency, ReturnCurrencies]) extends PoloniexSuccessResponse

case class ReturnCurrencies(
  id: BigInt,
  name: String,
  maxDailyWithdrawal: Option[BigInt],
  txFee: BigDecimal,
  minConf: BigInt,
  depositAddress: Option[String],
  disabled: Int,
  delisted: Int,
  frozen: Int
)
