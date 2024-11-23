package travelator.mobile

import java.util.*

class UserPreference @JvmOverloads constructor(
        var greeting: String = "Hello",
        var locale: Locale = Locale.UK,
        var currency: Currency = Currency.getInstance(Locale.UK)
)
