package travelator.opentosealed.itineraray

import java.util.*

enum class TravelMethod {
    SEA,
    RAIL,
    BUS,
    CAR,
    CAMEL;

    val userReadableName: String get() = name.lowercase(Locale.getDefault())
}