public class PatternExplainer {

    public String explain(RouteStrategy chosenStrategy) {
        String name = chosenStrategy.getName();
        return "\n--- Strategy Pattern Explanation ---\n"
                + "You chose the " + name + " strategy.\n"
                + "The Navigator (Context) did not calculate anything by itself. "
                + "It passed the work to the " + name + "Strategy object.\n"
                + "Every strategy implements the same RouteStrategy interface, "
                + "so the Navigator treats all of them the same way.\n"
                + "There is no if/else or switch in the Navigator. "
                + "The choice of algorithm is made once, by the Client, "
                + "when it calls setStrategy.\n"
                + "You can change the strategy at runtime. "
                + "Pick another option from the menu for the same route.\n"
                + "To add a new transport, you only write a new class that "
                + "implements RouteStrategy. The Navigator and the existing "
                + "strategies do not change.\n"
                + "-----------------------------------";
    }
}
