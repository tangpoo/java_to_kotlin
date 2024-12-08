package travelator.uncapsulatedcollection

import java.time.Duration

class Itinerary(
    val id: Id<Itinerary>,
    val route: Route
) : Route by route {

}

fun Iterable<Itinerary>.shortest() =
    minByOrNull {
        it.duration
    }

fun Route.hasJourneyLongerThan(duration: Duration) =
    any{ it.duration > duration }



fun Itinerary.withoutJourneysBy(travelMethod: TravelMethod) =
    Itinerary(
        id,
        this.filterNot { it.method == travelMethod }
    )