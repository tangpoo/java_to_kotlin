package travelator.trips;

import java.time.Instant;
import java.util.Optional;

public interface ITrackTrips {
    Optional<Trip> currentTripFor(String customerId, Instant at);
}
