package travelator.uncapsulatedcollection

import java.time.Duration

typealias Route = List<Journey>

fun Route(journeys: List<Journey>) = journeys

val Route.journeys get() = this

val Route.duration: Duration
    get() =
        Duration.between(
            get(0).departureTime,
            get(size - 1).arrivalTime
        )

val Route.arrivesAt: Location
    get() =
        get(size - 1).arrivesAt

fun <T> Iterable<T>.withItemAt(index: Int, replacedBy: T): List<T> =
    this.toMutableList().apply {
        this[index] = replacedBy
    }

val Route.departsFrom: Location
    get() =
        get(0).departsFrom
