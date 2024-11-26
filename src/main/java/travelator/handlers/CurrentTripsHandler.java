package travelator.handlers;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

import com.fasterxml.jackson.databind.ObjectMapper;
import travelator.http.Request;
import travelator.http.Response;
import travelator.trips.ITrackTrips;

public class CurrentTripsHandler {
    private final ITrackTrips tracking;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public CurrentTripsHandler(ITrackTrips tracking) {
        this.tracking = tracking;
    }
    public Response handle(Request request) {
        try {
            var customerId = request.getQueryParam("customerId").stream()
                .findFirst();
            if (customerId.isEmpty())
                return new Response(HTTP_BAD_REQUEST);
            var currentTrip = tracking.currentTripFor(customerId.get());
            return currentTrip.isPresent() ?
                new Response(HTTP_OK,
                    objectMapper.writeValueAsString(currentTrip)) :
                new Response(HTTP_NOT_FOUND);
        } catch (Exception x) {
            return new Response(HTTP_INTERNAL_ERROR);
        }
    }
}
