package travelator.uncapsulatedcollection

import java.time.Duration

data class Itinerary(
    val id: Id<Itinerary>,
    val route: Route
) : Route by route {

    fun withTransformedRoute(transform: (Route).() -> Route) =
        copy(route = transform(route))
}

fun Iterable<Itinerary>.shortest() =
    minByOrNull {
        it.duration
    }

fun Route.hasJourneyLongerThan(duration: Duration) =
    any{ it.duration > duration }



fun Itinerary.withoutJourneysBy(travelMethod: TravelMethod) =
    withTransformedRoute{
        filterNot { it.method == travelMethod }
    }

fun Itinerary.withoutLastJourney() =
    withTransformedRoute { dropLast(1) }