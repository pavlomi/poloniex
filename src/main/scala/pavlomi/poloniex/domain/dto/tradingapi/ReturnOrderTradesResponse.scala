package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnOrderTradesResponse(value: Seq[ReturnOrderTrades]) extends PoloniexSuccessResponse

case class ReturnOrderTrades(
  globalTradeID: Long,
  tradeID: Long,
  currencyPair: PoloniexCurrencyPair,
  `type`: String,
  rate: String,
  amount: String,
  total: String,
  fee: String,
  date: String
)
