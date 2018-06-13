package pavlomi.poloniex.domain.dto

trait PoloniexResponse

trait PoloniexSuccessResponse extends PoloniexResponse
trait PoloniexFailureResponse extends PoloniexResponse

case class PoloniexErrorResponse(error: String) extends PoloniexFailureResponse
