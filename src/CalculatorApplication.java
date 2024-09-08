import java.util.Scanner;

public class CalculatorApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to our Calculator");

        do {
            System.out.println("Choose an operation: (+, -, *, /, %): ");
            System.out.print("---> ");
            String input = sc.next();
            char ch = input.charAt(0);

            if (input.length() != 1) {
                System.out.println("Wrong input, please try again.");
            } else {
                chooseOperator(ch, sc);
            }

            // Retry option only 5 times
            boolean exit = retryLogic(sc);
            if (exit) {
                break;
            }
        } while (true);

        System.out.println("Thanks for using our service.");
        sc.close(); // Close scanner to release resources
    }

    public static boolean retryLogic(Scanner sc) {
        int retry = 5;

        for (int times = 0; times < retry; times++) {
            System.out.println("Do you want to continue (y/n): ");
            String exitInput = sc.next();
            char ch1 = exitInput.charAt(0);

            if (exitInput.length() == 1 && (ch1 == 'n' || ch1 == 'N')) {
                return true;
            } else if (exitInput.length() == 1 && (ch1 == 'y' || ch1 == 'Y')) {
                return false;
            } else {
                System.out.println("UNKNOWN. Please choose one of these: (y/n):");
            }
        }

        System.out.println("MAXIMUM count reached");

        return true;
    }

    public static void chooseOperator(char ch, Scanner sc) {
        int num1, num2;
        switch (ch) {
            case '+':
            case '-':
            case '*':
                num1 = getInteger(sc);
                num2 = getInteger(sc);
                break;
            case '/':
            case '%':
                num1 = getInteger(sc);
                num2 = getInteger(sc);
                if (num2 == 0) {
                    System.out.println(ch == '/' ? "Division by zero is undefined." : "Modulus by zero is undefined.");
                    return;
                }
                break;
            default:
                System.out.println("<--- INVALID OPERATOR --->");
                return;
        }

        switch (ch) {
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
                System.out.println("Division ---> " + (num1 / num2));
                break;
            case '%':
                System.out.println("Modulus ---> " + (num1 % num2));
                break;
        }
    }

    public static int getInteger(Scanner sc) {
        int retry = 5;

        for (int times = 0; times < retry; times++) {
            System.out.print("Enter an integer: ");
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
