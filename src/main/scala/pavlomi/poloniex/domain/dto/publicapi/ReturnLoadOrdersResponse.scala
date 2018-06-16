package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnLoadOrdersResponse(offers: Seq[Offer], demands: Seq[Demand]) extends PoloniexSuccessResponse

case class Offer(rate: BigDecimal, amount: String, rangeMin: Int, rangeMax: Int)
case class Demand(rate: BigDecimal, amount: String, rangeMin: Int, rangeMax: Int)
