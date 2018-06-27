package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class TransferBalanceResponse(success: Int, message: String) extends PoloniexSuccessResponse
