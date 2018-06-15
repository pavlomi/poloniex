package pavlomi.poloniex.domain.dto.publicapi

import pavlomi.poloniex.domain._

case class ReturnChartDataResponse(
  date: DateTimestamp,
  high: HighPrice,
  low: LowPrice,
  open: OpenPrice,
  close: ClosePrice,
  volume: BigDecimal,
  quoteVolume: QuoteVolume,
  weightedAverage: WeightedAverage
)
