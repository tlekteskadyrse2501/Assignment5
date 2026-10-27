import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleReader consoleReader = new ConsoleReader(scanner);
        PatternExplainer patternExplainer = new PatternExplainer();

        List<RouteStrategy> strategies = List.of(
                new CarStrategy(),
                new WalkStrategy(),
                new CycleStrategy(),
                new BusStrategy()
        );

        Navigator navigator = new Navigator(strategies.get(0));

        System.out.println("Welcome to the Route Planner!");
        System.out.println();

        boolean running = true;
        while (running) {
            String start = consoleReader.readNonEmptyText("Enter starting point: ");
            String destination = consoleReader.readNonEmptyText("Enter destination: ");
            double distanceKm = consoleReader.readPositiveNumber("Enter distance in kilometers: ");
            System.out.println();

            boolean choosingStrategy = true;
            while (choosingStrategy) {
                printMenu(strategies);
                int newRouteOption = strategies.size() + 1;
                int choice = consoleReader.readMenuChoice("Your choice: ", 0, newRouteOption);
                System.out.println();

                if (choice == 0) {
                    System.out.println("Goodbye!");
                    running = false;
                    choosingStrategy = false;
                } else if (choice == newRouteOption) {
                    choosingStrategy = false;
                } else {
                    RouteStrategy chosenStrategy = strategies.get(choice - 1);
                    navigator.setStrategy(chosenStrategy);
                    Route route = navigator.planRoute(start, destination, distanceKm);
                    System.out.println(route.toFormattedString());
                    System.out.println(patternExplainer.explain(chosenStrategy));
                    System.out.println();
                }
            }
        }

        scanner.close();
    }

    private static void printMenu(List<RouteStrategy> strategies) {
        System.out.println("Choose how you want to travel:");
        for (int i = 0; i < strategies.size(); i++) {
            System.out.println((i + 1) + ". " + strategies.get(i).getName());
        }
        System.out.println((strategies.size() + 1) + ". Enter a new route");
        System.out.println("0. Exit");
    }
}
