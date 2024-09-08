import java.util.Scanner;

public class ExampleCal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to our Calculator");

        do {
            char operation = getOperation(sc);

            if (operation != ' ') {
                executeOperation(operation, sc);
            }

            // Retry option only 5 times to continue or exit
            if (shouldExit(sc)) {
                break;
            }
        } while (true);

        System.out.println("Thanks for using our service.");
        sc.close(); // Close scanner to release resources
    }

    // Method to get the operation from the user
    public static char getOperation(@org.jetbrains.annotations.NotNull Scanner sc) {
        System.out.println("Choose an operation: (+, -, *, /, %): ");
        System.out.print("---> ");
        String input = sc.next();

        if (input.length() == 1) {
            char ch = input.charAt(0);
            if (isValidOperation(ch)) {
                return ch;
            }
        }
        System.out.println("Invalid input, please enter a valid operation.");
        return ' '; // Return an invalid character to indicate failure
    }

    // Method to check if the chosen operation is valid
    public static boolean isValidOperation(char operation) {
        return operation == '+' || operation == '-' || operation == '*' ||
                operation == '/' || operation == '%';
    }

    // Method to decide if the program should exit or not
    public static boolean shouldExit(Scanner sc) {
        int retry = 5;
        for (int times = 0; times < retry; times++) {
            System.out.print("Do you want to continue (y/n): ");
            String exitInput = sc.next();

            if (exitInput.equalsIgnoreCase("n")) {
                return true;
            } else if (exitInput.equalsIgnoreCase("y")) {
                return false;
            } else {
                System.out.println("Unknown input. Please choose 'y' for yes or 'n' for no.");
            }
        }
        System.out.println("Maximum retries reached. Exiting.");
        return true;
    }

    // Method to execute the chosen operation
    public static void executeOperation(char operation, Scanner sc) {
        int num1 = getInteger(sc, "Enter the first integer: ");
        int num2 = getInteger(sc, "Enter the second integer: ");

        switch (operation) {
            case '+':
                System.out.println("Addition ---> " + (num1 + num2));
                break;
            case '-':
                System.out.println("Subtraction ---> " + (num1 - num2));
                break;
            case '*':
                System.out.println("Multiplication ---> " + (num1 * num2));
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Division by zero is undefined.");
                } else {
                    System.out.println("Division ---> " + (num1 / num2));
                }
                break;
            case '%':
                if (num2 == 0) {
                    System.out.println("Modulus by zero is undefined.");
                } else {
                    System.out.println("Modulus ---> " + (num1 % num2));
                }
                break;
        }
    }

    // Method to get a valid integer from the user
    public static int getInteger(Scanner sc, String prompt) {
        int retry = 5;

        for (int times = 0; times < retry; times++) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Clear the invalid input
            }
        }

        System.out.println("Maximum attempts reached. Exiting.");
        System.out.println("Thanks for using our service.");
        System.exit(1); // Exit if the maximum attempts are reached
        return -1; // This line will never be reached, but it's needed for compilation
    }
}
