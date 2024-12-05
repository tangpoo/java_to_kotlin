package travelator.itinerary

import travelator.money.CurrencyConversion
import travelator.money.Money
import travelator.money.Money.Companion.of
import java.util.*

class CostSummary(
    userCurrency: Currency,
    val lines: List<CurrencyConversion>
) {
    var total: Money = of(0, userCurrency)
        private set

    init {
        lines.forEach {
            total += it.toMoney
        }
    }
}
