package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class CancelLoanOfferResponse(success: Int, message: String) extends PoloniexSuccessResponse
