package travelator.itinerary

import travelator.money.ExchangeRates
import travelator.money.Money
import java.util.*

class CostSummaryCalculator(
    private val userCurrency: Currency,
    private val exchangeRates: ExchangeRates
) {
    private val currencyTotals: MutableMap<Currency, Money> = HashMap()

    fun addCost(cost: Money) {
        currencyTotals.merge(cost.currency, cost, Money::add)
    }

    fun summarise(): CostSummary {
        val conversions = currencyTotals.values.sortedBy {
            it.currency.currencyCode
        }.map { exchangeRates.convert(it, userCurrency) }

        return CostSummary(conversions, conversions
            .map { it.toMoney }
            .fold(Money.of(0, userCurrency), Money::add))
    }

    fun summarise(costs: Iterable<Money>): CostSummary {
        val delegate = CostSummaryCalculator(userCurrency, exchangeRates)
        costs.forEach(delegate::addCost)
        return delegate.summarise()
    }

    fun reset() {
        currencyTotals.clear()
    }
}
