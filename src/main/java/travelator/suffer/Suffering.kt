package travelator.suffer

import java.util.stream.Collectors
import kotlin.math.min

object Suffering {

    @JvmStatic
    fun sufferScoreFor(route: List<Journey>): Int {
        return sufferScore(
                longestJourneysIn(route, 3),
                Routes.getDepartsFrom(route))
    }

    @JvmStatic
    fun longestJourneysIn(
            journeys: List<Journey>,
            limit: Int
    ): List<Journey> {
        val actualLimit = min(journeys.size.toDouble(), limit.toDouble()).toInt()
        return Collections.sorted(
                journeys,
                Comparator.comparing { obj: Journey -> obj.duration }.reversed()
        ).subList(0, actualLimit)
    }

    fun routesToShowFor(itineraryId: String?): List<List<Journey>> {
        return bearable(Other.routesFor(itineraryId))
    }

    private fun bearable(routes: List<List<Journey>>
    ): List<List<Journey>> {
        return routes.stream()
                .filter { route: List<Journey> -> sufferScoreFor(route) <= 10 }
                .collect(Collectors.toUnmodifiableList())
    }

    private fun sufferScore(
            longestJourneys: List<Journey>,
            start: Location
    ): Int {
        return Other.SOME_COMPLICATED_RESULT()
    }
}