package travelator

import travelator.trips.Trip
import travelator.trips.Trips
import java.time.Instant

class InMemoryTrips : Trips {
    private val trips: MutableMap<String, MutableSet<Trip>> = mutableMapOf()
    fun addTrip(trip: Trip) {
        val existingTrips = trips.getOrDefault(
                trip.customerId,
                mutableSetOf()
        )
        existingTrips.add(trip)
        trips[trip.customerId] = existingTrips
    }

    override fun tripsFor(customerId: String): Set<Trip> =
            trips.getOrDefault(customerId, emptySet())

    override fun currentTripsFor(customerId: String, at: Instant): Set<Trip> =
            tripsFor(customerId)
                    .filter { it.isPlannedToBeActiveAt(at) }
                    .toSet()
}

