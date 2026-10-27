public class Route {

    private final String transportName;
    private final String start;
    private final String destination;
    private final double distanceKm;
    private final long travelMinutes;
    private final double cost;

    public Route(String transportName, String start, String destination,
                 double distanceKm, long travelMinutes, double cost) {
        this.transportName = transportName;
        this.start = start;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.travelMinutes = travelMinutes;
        this.cost = cost;
    }

    public String getTransportName() {
        return transportName;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public long getTravelMinutes() {
        return travelMinutes;
    }

    public double getCost() {
        return cost;
    }

    public String toFormattedString() {
        return "Route: " + start + " -> " + destination + "\n"
                + "Transport: " + transportName + "\n"
                + String.format("Distance: %.1f km", distanceKm) + "\n"
                + "Travel time: " + travelMinutes + " minutes" + "\n"
                + String.format("Cost: %.1f KZT", cost);
    }
}
