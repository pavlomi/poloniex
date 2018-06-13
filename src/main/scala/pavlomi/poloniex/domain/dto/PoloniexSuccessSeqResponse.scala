package pavlomi.poloniex.domain.dto

case class PoloniexSuccessSeqResponse[T](value: Seq[T]) extends PoloniexSuccessResponse
