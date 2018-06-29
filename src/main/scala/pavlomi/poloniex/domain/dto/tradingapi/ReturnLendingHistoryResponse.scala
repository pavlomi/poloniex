package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency

case class ReturnLendingHistoryResponse(
  id: BigInt,
  currency: PoloniexCurrency,
  rate: String,
  amount: String,
  duration: String,
  interest: String,
  fee: String,
  earned: String,
  open: String,
  close: String
)
