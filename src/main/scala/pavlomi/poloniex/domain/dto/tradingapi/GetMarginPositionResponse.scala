package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class GetMarginPositionResponse(
  amount: String,
  total: String,
  basePrice: String,
  liquidationPrice: Int,
  pl: String,
  lendingFees: String,
  `type`: String
) extends PoloniexSuccessResponse
