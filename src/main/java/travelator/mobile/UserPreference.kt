package travelator.mobile

import java.util.*

data class UserPreference(
        val greeting: String = "Hello",
        val locale: Locale = Locale.UK,
        val currency: Currency = Currency.getInstance(Locale.UK)
)
