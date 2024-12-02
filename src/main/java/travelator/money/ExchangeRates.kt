package travelator.money

import travelator.money.Money.Companion.of
import java.math.BigDecimal
import java.util.*

interface ExchangeRates {
    fun rate(fromCurrency: Currency?, toCurrency: Currency?): BigDecimal

    fun convert(fromMoney: Money, toCurrency: Currency?): CurrencyConversion? {
        val rate = rate(fromMoney.currency, toCurrency)
        val toAmount = fromMoney.amount.multiply(rate)
        val toMoney = of(toAmount, toCurrency!!)

        return CurrencyConversion(fromMoney, toMoney)
    }
}
