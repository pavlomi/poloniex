package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrencyPair
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

trait ReturnTradeHistoryResponse                                                            extends PoloniexSuccessResponse
case class ReturnTradeHistorySingle(value: Seq[ReturnTradeHistory])                         extends ReturnTradeHistoryResponse
case class ReturnTradeHistoryAll(value: Map[PoloniexCurrencyPair, Seq[ReturnTradeHistory]]) extends ReturnTradeHistoryResponse

case class ReturnTradeHistory(
  globalTradeID: Long,
  tradeID: String,
  date: String,
  rate: String,
  amount: String,
  total: String,
  fee: String,
  orderNumber: String,
  `type`: String,
  category: String
)
