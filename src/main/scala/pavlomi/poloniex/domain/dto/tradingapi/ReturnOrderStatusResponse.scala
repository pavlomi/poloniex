package pavlomi.poloniex.domain.dto.tradingapi
import pavlomi.poloniex.domain.dto.PoloniexSuccessResponse

case class ReturnOrderStatusResponse(result: Map[String, Result], success: Int) extends PoloniexSuccessResponse

case class Result(
  status: String,
  rate: String,
  amount: String,
  currencyPair: String,
  date: String,
  total: String,
  `type`: String,
  startingAmount: String
)
