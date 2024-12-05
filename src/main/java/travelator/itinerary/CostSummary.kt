package travelator.itinerary

import travelator.money.CurrencyConversion
import travelator.money.Money
import travelator.money.Money.Companion.of
import java.util.*

class CostSummary(
    userCurrency: Currency,
    val lines: List<CurrencyConversion>
) {
    val total = lines
        .map { it.toMoney }
        .fold(Money.of(0, userCurrency), Money::add)
}
