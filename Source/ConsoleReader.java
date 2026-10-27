import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonEmptyText(String question) {
        while (true) {
            System.out.print(question);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public double readPositiveNumber(String question) {
        while (true) {
            System.out.print(question);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number greater than zero.");
            } catch (NumberFormatException exception) {
                System.out.println("That is not a valid number. Please try again.");
            }
        }
    }

    public int readMenuChoice(String question, int minimum, int maximum) {
        while (true) {
            System.out.print(question);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= minimum && value <= maximum) {
                    return value;
                }
                System.out.println("Please enter a number between " + minimum + " and " + maximum + ".");
            } catch (NumberFormatException exception) {
                System.out.println("That is not a valid number. Please try again.");
            }
        }
    }
}
