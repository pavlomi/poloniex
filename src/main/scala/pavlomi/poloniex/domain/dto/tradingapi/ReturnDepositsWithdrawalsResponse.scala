package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnDepositsWithdrawalsResponse(deposits: Seq[ReturnDepositsWithdrawals]) extends PoloniexSuccessResponse

case class ReturnDepositsWithdrawals(
  currency: PoloniexCurrency,
  address: String,
  amount: String,
  confirmations: Int,
  txid: String,
  timestamp: Long,
  status: String
)
