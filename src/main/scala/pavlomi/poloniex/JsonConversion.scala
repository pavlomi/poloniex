package pavlomi.poloniex

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import pavlomi.poloniex.domain.{PoloniexCurrency, PoloniexCurrencyPair}
import pl.iterators.kebs.enums.KebsEnums
import pl.iterators.kebs.json.KebsSpray
import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat}

import scala.util.Try

trait JsonConversion extends DefaultJsonProtocol with SprayJsonSupport with KebsSpray with KebsEnums {
  implicit val poloniexCurrencyPairJsonFormat = new JsonFormat[PoloniexCurrencyPair] {
    override def read(json: JsValue): PoloniexCurrencyPair = json match {
      case JsString(poloniexCurrencyPairStr) =>
        Try {
          val Seq(counter, base) = poloniexCurrencyPairStr.split("_").toSeq
          PoloniexCurrencyPair(PoloniexCurrency.withValue(counter), PoloniexCurrency.withValue(base))
        }.getOrElse(??? /*deserializationError("Expected UUID format")*/ )
      case _ => ??? //deserializationError("Expected UUID format")
    }

    override def write(obj: PoloniexCurrencyPair): JsValue = JsString(obj.toString)
  }
}

object JsonConversion extends JsonConversion
