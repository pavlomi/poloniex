package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnMarginAccountSummaryResponse(
  totalValue: String,
  pl: String,
  lendingFees: String,
  netValue: String,
  totalBorrowedValue: String,
  currentMargin: String
) extends PoloniexSuccessResponse
