public class WalkStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KMH = 5;
    private static final double COST = 0;
    private static final int MINUTES_IN_HOUR = 60;

    public Route buildRoute(String start, String destination, double distanceKm) {
        long travelMinutes = Math.round(distanceKm / AVERAGE_SPEED_KMH * MINUTES_IN_HOUR);
        return new Route(getName(), start, destination, distanceKm, travelMinutes, COST);
    }

    public String getName() {
        return "Walk";
    }
}
