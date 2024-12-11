package travelator.interfacetofun.recommendations

import travelator.interfacetofun.destinations.FeaturedDestination
import travelator.interfacetofun.destinations.FeaturedDestinations
import travelator.interfacetofun.domain.DistanceCalculator
import travelator.interfacetofun.domain.Location
import java.util.stream.Collectors

class Recommendations(
    private val destinationFinder: (Location) -> List<FeaturedDestination>,
    private val distanceInMeterBetween: (Location, Location) -> Int
) {
    fun recommendationsFor(
        journey: Set<Location>
    ): List<FeaturedDestinationSuggestion> =
        journey
            .flatMap { location -> recommendationsFor(location) }
            .deduplicated()
            .sortedBy { it.distanceMeters }

    fun recommendationsFor(
        location: Location
    ): List<FeaturedDestinationSuggestion> =
        destinationFinder(location)
        .map { featuredDestination ->
            FeaturedDestinationSuggestion(
                location,
                featuredDestination,
                distanceInMeterBetween(
                    location,
                    featuredDestination.location
                )
            )
        }
}

private fun List<FeaturedDestinationSuggestion>.closestToJourneyLocation() =
    minByOrNull { it.distanceMeters } ?: error("Unexpected empty group")

private fun List<FeaturedDestinationSuggestion>.deduplicated() =
    groupBy { it.suggestion }
        .values
        .map { suggestionsWithSameDestination ->
            suggestionsWithSameDestination.closestToJourneyLocation()
        }
