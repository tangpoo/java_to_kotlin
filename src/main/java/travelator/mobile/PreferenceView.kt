package travelator.mobile

import java.util.*

class PreferenceView : View() {
    private val greetingPicker = GreetingPicker()
    private val localePicker = LocalePicker()
    private val currencyPicker = CurrencyPicker()

    fun showModal(preferences: UserPreference): UserPreference {
        greetingPicker.greeting = preferences.greeting
        localePicker.locale = preferences.locale
        currencyPicker.currency = preferences.currency
        super.show()
        return preferences
    }
}

internal class GreetingPicker {
    var greeting: String? = ""
}

internal class LocalePicker {
    var locale: Locale? = Locale.UK
}

internal class CurrencyPicker {
    var currency: Currency = Currency.getInstance(Locale.UK)
}