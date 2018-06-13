package pavlomi.poloniex.domain.dto

import pavlomi.poloniex.domain._

case class ReturnChartDataResponse(
    date: DateTimestamp,
    high: HighPrice,
    low: LowPrice,
    open: OpenPrice,
    close: ClosePrice,
    value: Value,
    quoteValue: QuoteValue,
    weightAverage: WeightAverage
)
