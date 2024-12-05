package travelator.itinerary

import travelator.money.CurrencyConversion
import travelator.money.Money
import travelator.money.Money.Companion.of
import java.util.*

class CostSummary(userCurrency: Currency) {
    private val lines: MutableList<CurrencyConversion> = ArrayList()
    var total: Money = of(0, userCurrency)
        private set

    fun addLine(line: CurrencyConversion) {
        lines.add(line)
        total += line.toMoney
    }

    fun lines(): List<CurrencyConversion> = lines.toList()
}
