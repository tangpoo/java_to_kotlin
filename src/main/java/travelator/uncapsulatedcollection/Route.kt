package travelator.uncapsulatedcollection

import java.time.Duration

typealias Route = List<Journey>

fun Route(journeys: List<Journey>) = journeys

val Route.journeys get() = this

val Route.size: Int
    get() = journeys.size

operator fun Route.get(index: Int): Journey = journeys[index]

val Route.duration: Duration
    get() =
        Duration.between(
            get(0).departureTime,
            get(size - 1).arrivalTime
        )

val Route.arrivesAt: Location
    get() =
        get(size - 1).arrivesAt

fun Route.withJourneyAt(index: Int, replacedBy: Journey): Route =
    Route(journeys.withItemAt(index, replacedBy))

fun <T> Iterable<T>.withItemAt(index: Int, replacedBy: T): List<T> =
    this.toMutableList().apply {
        this[index] = replacedBy
    }

val Route.departsFrom: Location
    get() =
        get(0).departsFrom
