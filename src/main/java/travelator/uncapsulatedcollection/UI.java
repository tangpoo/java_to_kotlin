package travelator.uncapsulatedcollection;


import java.time.Duration;
public class UI {
    public void render(Iterable<Journey> route) {
        for (var journey : route) {
            render(journey);
        }
    }
    public void render(Route route) {
        for (int i = 0; i < route.size(); i++) {
            var journey = route.get(i);
            render(journey);
        }
    }
    public void renderWithHeader(Route route) {
        renderHeader(
            RouteKt.getDepartsFrom(route),
            route.getArrivesAt(),
            route.getDuration()
        );
        for (int i = 0; i < route.size(); i++) {
            var journey = route.get(i);
            render(journey);
        }
    }
    private void render(Journey journey) {
    }
    private void renderHeader(
        Location departsFrom,
        Location arrivesAt,
        Duration duration) {
    }
}
