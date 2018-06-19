package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class GenerateNewAddressResponse(success: Long, response: String) extends PoloniexSuccessResponse
