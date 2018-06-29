package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.PoloniexCurrency
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnActiveLoansResponse(
  provided: Seq[ActiveLoan],
  used: Seq[ActiveLoan]
) extends PoloniexSuccessResponse

case class ActiveLoan(
  id: BigInt,
  currency: PoloniexCurrency,
  rate: String,
  amount: String,
  range: Int,
  autoRenew: Int,
  date: String,
  fees: String
)
