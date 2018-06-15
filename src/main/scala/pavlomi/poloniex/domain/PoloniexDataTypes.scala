package pavlomi.poloniex.domain

case class DateTimestamp(value: Long)       extends AnyVal
case class HighPrice(value: BigDecimal)     extends AnyVal
case class LowPrice(value: BigDecimal)      extends AnyVal
case class OpenPrice(value: BigDecimal)     extends AnyVal
case class ClosePrice(value: BigDecimal)    extends AnyVal
case class Value(value: BigDecimal)         extends AnyVal
case class QuoteVolume(value: BigDecimal)    extends AnyVal
case class WeightedAverage(value: BigDecimal) extends AnyVal

case class PoloniexAPIKey(value: String) extends AnyVal
case class PoloniexSecret(value: String) extends AnyVal

case class PoloniexCurrency(value: String) extends AnyVal

case class PoloniexCurrencyPair(counter: PoloniexCurrency, base: PoloniexCurrency) {
  override def toString: String = counter.value + "_" + base.value
}

object PoloniexCurrencyPair {
  def from(counter: String, base: String): PoloniexCurrencyPair = PoloniexCurrencyPair(PoloniexCurrency(counter), PoloniexCurrency(base))
}
