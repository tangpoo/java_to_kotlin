package travelator.interfacetofun.recommendations

import travelator.interfacetofun.destinations.FeaturedDestination
import travelator.interfacetofun.domain.Location

data class FeaturedDestinationSuggestion(
    val routeLocation: Location,
    val suggestion: FeaturedDestination,
    val distanceMeters: Int
)