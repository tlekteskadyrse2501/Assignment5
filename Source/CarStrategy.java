public class CarStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KMH = 50;
    private static final double COST_PER_KM = 25;
    private static final int MINUTES_IN_HOUR = 60;

    public Route buildRoute(String start, String destination, double distanceKm) {
        long travelMinutes = Math.round(distanceKm / AVERAGE_SPEED_KMH * MINUTES_IN_HOUR);
        double cost = distanceKm * COST_PER_KM;
        return new Route(getName(), start, destination, distanceKm, travelMinutes, cost);
    }

    public String getName() {
        return "Car";
    }
}
