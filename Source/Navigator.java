public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public Route planRoute(String start, String destination, double distanceKm) {
        return strategy.buildRoute(start, destination, distanceKm);
    }
}
