package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnTradeHistoryResponse(value: Seq[ReturnTradeHistory]) extends PoloniexSuccessResponse
case class ReturnTradeHistory(
  date: String,
  `type`: String,
  rate: String,
  amount: String,
  total: String
)
