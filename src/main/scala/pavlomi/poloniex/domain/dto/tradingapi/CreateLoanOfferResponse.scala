package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class CreateLoanOfferResponse(success: Int, message: String, orderID: BigInt) extends PoloniexSuccessResponse
