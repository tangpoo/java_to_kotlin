package travelator.suffer;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;
import static travelator.suffer.Collections.sorted;
import static travelator.suffer.Other.SOME_COMPLICATED_RESULT;
import static travelator.suffer.Other.routesFor;
import static travelator.suffer.Routes.getDepartsFrom;

import java.util.List;

public class Suffering {
    public static int sufferScoreFor(List<Journey> route) {
        return sufferScore(
            longestJourneysIn(route, 3),
            getDepartsFrom(route));
    }
    static List<Journey> longestJourneysIn(
        List<Journey> journeys,
        int limit
    ) {
        var actualLimit = Math.min(journeys.size(), limit);
        return sorted(
            journeys,
            comparing(Journey::getDuration).reversed()
        ).subList(0, actualLimit);
    }
    public static List<List<Journey>> routesToShowFor(String itineraryId) {
        return bearable(routesFor(itineraryId));
    }
    private static List<List<Journey>> bearable
        (List<List<Journey>> routes
        ) {
        return routes.stream()
            .filter(route -> sufferScoreFor(route) <= 10)
            .collect(toUnmodifiableList());
    }
    private static int sufferScore(
        List<Journey> longestJourneys,
        Location start
    ) {
        return SOME_COMPLICATED_RESULT();
    }
}