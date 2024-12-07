package travelator.uncapsulatedcollection;


import java.time.Duration;
public class UI {
    public void render(Iterable<Journey> route) {
        for (var journey : route) {
            render(journey);
        }
    }
    public void render(Route route) {
        for (int i = 0; i < RouteKt.getSize(route); i++) {
            var journey = RouteKt.get(route, i);
            render(journey);
        }
    }
    public void renderWithHeader(Route route) {
        renderHeader(
            RouteKt.getDepartsFrom(route),
            RouteKt.getArrivesAt(route),
            RouteKt.getDuration(route)
        );
        for (int i = 0; i < RouteKt.getSize(route); i++) {
            var journey = RouteKt.get(route, i);
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
