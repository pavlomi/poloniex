package pavlomi.poloniex.domain.dto.tradingapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnFeeInfoResponse(makerFee: String, takerFee: String, thirtyDayVolume: String, nextTier: BigInt) extends PoloniexSuccessResponse
