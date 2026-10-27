public interface RouteStrategy {

    Route buildRoute(String start, String destination, double distanceKm);

    String getName();
}
