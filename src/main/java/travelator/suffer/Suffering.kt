package travelator.suffer

import java.util.stream.Collectors

object Suffering {

    @JvmStatic
    fun sufferScoreFor(route: List<Journey>): Int {
        return sufferScore(
                route.longestJourneysIn(limit = 3),
                Routes.getDepartsFrom(route))
    }

    @JvmStatic
    fun List<Journey>.longestJourneysIn(limit: Int): List<Journey> =
            sortedByDescending { it.duration }.take(limit)

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