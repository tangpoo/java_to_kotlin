package travelator.trips;

import java.util.Optional;

public interface ITrackTrips {
    Optional<Trip> currentTripFor(String customerId);
}
