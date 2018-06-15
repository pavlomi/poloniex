package pavlomi.poloniex

import pavlomi.poloniex.domain.{PoloniexCurrency, PoloniexCurrencyPair}
import pl.iterators.kebs.json.{KebsEnumFormats, KebsSpray}
import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat}

import scala.util.Try

trait JsonConversion extends DefaultJsonProtocol with KebsSpray with KebsEnumFormats {
  implicit val poloniexCurrencyPairJsonFormat = new JsonFormat[PoloniexCurrencyPair] {
    override def read(json: JsValue): PoloniexCurrencyPair = json match {
      case JsString(poloniexCurrencyPairStr) =>
        val currencies = poloniexCurrencyPairStr.split("_")
        Try {
          PoloniexCurrencyPair(PoloniexCurrency(currencies(0)), PoloniexCurrency(currencies(1)))
        }.getOrElse(??? /*deserializationError("Expected UUID format")*/ )
      case _ => ??? //deserializationError("Expected UUID format")
    }

    override def write(obj: PoloniexCurrencyPair): JsValue = JsString(obj.toString)
  }
}

object JsonConversion extends JsonConversion
