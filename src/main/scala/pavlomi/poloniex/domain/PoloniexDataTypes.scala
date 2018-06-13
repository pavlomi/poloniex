package pavlomi.poloniex.domain

import enumeratum.values.{StringEnum, StringEnumEntry}

case class DateTimestamp(value: Long)       extends AnyVal
case class HighPrice(value: BigDecimal)     extends AnyVal
case class LowPrice(value: BigDecimal)      extends AnyVal
case class OpenPrice(value: BigDecimal)     extends AnyVal
case class ClosePrice(value: BigDecimal)    extends AnyVal
case class Value(value: BigDecimal)         extends AnyVal
case class QuoteValue(value: BigDecimal)    extends AnyVal
case class WeightAverage(value: BigDecimal) extends AnyVal

case class PoloniexAPIKey(value: String) extends AnyVal
case class PoloniexSecret(value: String) extends AnyVal

sealed abstract class PoloniexCurrency(val value: String, val name: String) extends StringEnumEntry
case object PoloniexCurrency extends StringEnum[PoloniexCurrency] {
  case object BTC  extends PoloniexCurrency("BTC", "Bitcoin")
  case object USDT extends PoloniexCurrency("USDT", "USD Dollar")
  case object BCH  extends PoloniexCurrency("BCH", "Bitcoin Cash")
  case object XMR  extends PoloniexCurrency("XMR", "Monero")
  case object XRP  extends PoloniexCurrency("XRP", "Ripple")
  case object ETH  extends PoloniexCurrency("ETH", "Ethereum")
  case object LTC  extends PoloniexCurrency("LTC", "Litecoin")
  case object ZEC  extends PoloniexCurrency("ZEC", "Zcash")

  val values = findValues
}

case class PoloniexCurrencyPair(counter: PoloniexCurrency, base: PoloniexCurrency) {
  override def toString: String = counter.value + "_" + base.value
}
