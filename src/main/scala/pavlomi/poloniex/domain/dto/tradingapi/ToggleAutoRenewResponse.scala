package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ToggleAutoRenewResponse(success: Int, message: Int) extends PoloniexSuccessResponse
