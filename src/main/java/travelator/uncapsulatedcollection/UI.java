package travelator.uncapsulatedcollection;


import java.time.Duration;
import java.util.List;

public class UI {
    public void render(Iterable<Journey> route) {
        for (var journey : route) {
            render(journey);
        }
    }
    public void render(List<Journey> route) {
        for (Journey journey : route) {
            render(journey);
        }
    }
    public void renderWithHeader(List<Journey> route) {
        renderHeader(
            RouteKt.getDepartsFrom(route),
            RouteKt.getArrivesAt(route),
            RouteKt.getDuration(route)
        );
        for (Journey journey : route) {
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
