package travelator.itinerary

import travelator.money.CurrencyConversion
import travelator.money.Money
import travelator.money.Money.Companion.of
import java.util.*

class CostSummary(userCurrency: Currency) {
    private val _lines = mutableListOf<CurrencyConversion>()
    var total: Money = of(0, userCurrency)
        private set

    val lines: List<CurrencyConversion>
        get() = _lines.toList()

    constructor(
        userCurrency: Currency,
        lines: List<CurrencyConversion>
    ): this(userCurrency) {
        lines.forEach(::addLine)
    }

    fun addLine(line: CurrencyConversion) {
        _lines.add(line)
        total += line.toMoney
    }
}
