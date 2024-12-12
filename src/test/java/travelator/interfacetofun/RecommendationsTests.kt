package travelator.interfacetofun

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import travelator.interfacetofun.destinations.FeaturedDestination
import travelator.interfacetofun.domain.Location
import travelator.interfacetofun.recommendations.FeaturedDestinationSuggestion
import travelator.interfacetofun.recommendations.Recommendations
import java.util.Set

class RecommendationsTests {
    private val featuredDestinations =
        mutableMapOf<Location, List<FeaturedDestination>>()
            .withDefault { emptyList() }
    private val distanceInMetersBetween =
        mutableMapOf<Pair<Location, Location>, Int>()
            .withDefault { -1 }

    private val recommendations = Recommendations(
        featuredDestinations::getValue,
        distanceInMetersBetween::getValue
    )

    private val paris = location("Paris")
    private val louvre = featured("Louvre", "Rue de Rivoli")
    private val eiffelTower = featured("Eiffel Tower", "Champ de Mars")
    private val alton = location("Alton")
    private val froyle = location("Froyle")
    private val watercressLine = featured("Watercress Line", "Alton Station")
    private val flowerFarm = featured("West End Flower Farm", froyle)

    @Test
    fun returns_no_recommendations_when_no_locations() {
        Assertions.assertEquals(
            emptyList<Any>(),
            recommendations.recommendationsFor(emptySet())
        )
    }

    @Test
    fun returns_no_recommendations_when_no_featured() {
        givenFeaturedDestinationsFor(paris, emptyList())
        Assertions.assertEquals(
            emptyList<Any>(),
            recommendations.recommendationsFor(Set.of(paris))
        )
    }

    @Test
    fun returns_recommendations_for_single_location() {
        givenFeaturedDestinationsFor(
            paris,
            java.util.List.of(
                eiffelTower,
                louvre
            )
        )
        givenADistanceBetween(paris, eiffelTower, 5000)
        givenADistanceBetween(paris, louvre, 1000)
        Assertions.assertEquals(
            java.util.List.of(
                FeaturedDestinationSuggestion(paris, louvre, 1000),
                FeaturedDestinationSuggestion(paris, eiffelTower, 5000)
            ),
            recommendations.recommendationsFor(Set.of(paris))
        )
    }

    @Test
    fun returns_recommendations_for_multi_location() {
        givenFeaturedDestinationsFor(
            paris,
            java.util.List.of(
                eiffelTower,
                louvre
            )
        )
        givenADistanceBetween(paris, eiffelTower, 5000)
        givenADistanceBetween(paris, louvre, 1000)
        givenFeaturedDestinationsFor(
            alton,
            java.util.List.of(
                flowerFarm,
                watercressLine
            )
        )
        givenADistanceBetween(alton, flowerFarm, 5300)
        givenADistanceBetween(alton, watercressLine, 320)
        Assertions.assertEquals(
            java.util.List.of(
                FeaturedDestinationSuggestion(alton, watercressLine, 320),
                FeaturedDestinationSuggestion(paris, louvre, 1000),
                FeaturedDestinationSuggestion(paris, eiffelTower, 5000),
                FeaturedDestinationSuggestion(alton, flowerFarm, 5300)
            ),
            recommendations.recommendationsFor(Set.of(paris, alton))
        )
    }

    @Test
    fun deduplicates_using_smallest_distance() {
        givenFeaturedDestinationsFor(
            alton,
            java.util.List.of(
                flowerFarm,
                watercressLine
            )
        )
        givenADistanceBetween(alton, flowerFarm, 5300)
        givenADistanceBetween(alton, watercressLine, 320)
        givenFeaturedDestinationsFor(
            froyle,
            java.util.List.of(
                flowerFarm,
                watercressLine
            )
        )
        givenADistanceBetween(froyle, flowerFarm, 0)
        givenADistanceBetween(froyle, watercressLine, 6300)
        Assertions.assertEquals(
            java.util.List.of(
                FeaturedDestinationSuggestion(froyle, flowerFarm, 0),
                FeaturedDestinationSuggestion(alton, watercressLine, 320)
            ),
            recommendations.recommendationsFor(Set.of(alton, froyle))
        )
    }

    private fun location(name: String): Location {
        return Location(Id.of(name), name, name)
    }

    private fun featured(name: String, locationName: String): FeaturedDestination {
        return featured(name, location(locationName))
    }

    private fun featured(name: String, location: Location): FeaturedDestination {
        return FeaturedDestination(name, location)
    }

    private fun givenFeaturedDestinationsFor(
        location: Location,
        destinations: List<FeaturedDestination>
    ) {
        featuredDestinations[location] = destinations.toList()
    }

    private fun givenADistanceBetween(
        location: Location,
        destination: FeaturedDestination,
        distanceInMeters: Int
    ) {
        distanceInMetersBetween[location to destination.location] = distanceInMeters
    }
}

private fun <K1, K2, V> Map<Pair<K1, K2>, V>.getValue(k1: K1, k2: K2) =
    getValue(k1 to k2)