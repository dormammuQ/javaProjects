import java.util.Scanner;
import java.util.Stack;

public class AdvancedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueChoice;

        // Greeting message
        System.out.println("Welcome to the Advanced Calculator!");

        do {
            // Get and evaluate the expression
            String expression = getInputExpression(scanner);
            try {
                double result = evaluateExpression(expression);
                System.out.println("Result ---> " + formatResult(result));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Ask the user if they want to perform another calculation
            continueChoice = getContinueChoice(scanner);

        } while (continueChoice.equalsIgnoreCase("y"));

        // Farewell message
        System.out.println("Thank you for using the Advanced Calculator! Goodbye!");
        scanner.close();
    }

    // Method to get the expression input from the user
    private static String getInputExpression(Scanner scanner) {
        System.out.print("Enter an expression (e.g., 3+5*2, (4+3)*2, sqrt(16), sin(45), etc.): ");
        return scanner.nextLine().replaceAll("\\s+", ""); // Remove spaces
    }

    // Method to ask the user if they want to continue
    private static String getContinueChoice(Scanner scanner) {
        String choice;
        do {
            System.out.print("Would you like to perform another calculation? (y/n): ");
            choice = scanner.nextLine().trim();
        } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
        return choice;
    }

    // Method to evaluate the expression using BODMAS rules
    private static double evaluateExpression(String expression) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                // Parse number (including decimals)
                i = parseNumber(expression, values, i);

            } else if (c == '(') {
                operators.push(c);

            } else if (c == ')') {
                // Solve the entire expression inside parentheses
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Remove the opening parenthesis

            } else if (isOperator(c)) {
                // Process all operators with higher or equal precedence
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);

            } else if (Character.isLetter(c)) {
                // Handle functions like sqrt, sin, cos, etc.
                i = parseFunction(expression, values, i);

            } else {
                throw new Exception("Invalid character encountered: " + c);
            }
        }

        // Apply remaining operators
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        if (values.size() != 1) {
            throw new Exception("Invalid expression.");
        }

        return values.pop();
    }

    // Method to parse a number (including decimals) from the expression
    private static int parseNumber(String expression, Stack<Double> values, int index) {
        StringBuilder sb = new StringBuilder();
        int length = expression.length();

        while (index < length && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
            sb.append(expression.charAt(index++));
        }
        values.push(Double.parseDouble(sb.toString()));
        return index - 1;
    }

    // Method to parse and apply mathematical functions (like sqrt, sin, etc.)
    private static int parseFunction(String expression, Stack<Double> values, int index) throws Exception {
        StringBuilder sb = new StringBuilder();
        int length = expression.length();

        while (index < length && Character.isLetter(expression.charAt(index))) {
            sb.append(expression.charAt(index++));
        }

        String function = sb.toString();
        if (index < length && expression.charAt(index) == '(') {
            index++;
            applyFunction(function, values);
        } else {
            throw new Exception("Expected '(' after function: " + function);
        }

        return index - 1;
    }

    // Method to apply a mathematical function using enhanced switch
    private static void applyFunction(String function, Stack<Double> values) throws Exception {
        double val = values.pop();

        values.push(switch (function) {
            case "sqrt" -> Math.sqrt(val);
            case "sin" -> Math.sin(Math.toRadians(val));
            case "cos" -> Math.cos(Math.toRadians(val));
            case "tan" -> Math.tan(Math.toRadians(val));
            case "log" -> Math.log(val);
            case "log10" -> Math.log10(val);
            case "exp" -> Math.exp(val);
            case "abs" -> Math.abs(val);
            case "fact" -> factorial(val);
            case "inv" -> 1 / val;
            case "neg" -> -val;
            default -> throw new Exception("Unknown function: " + function);
        });
    }

    // Method to apply an operator to operands using enhanced switch
    private static double applyOperator(char op, double b, double a) throws Exception {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new Exception("Division by zero is undefined.");
                yield a / b;
            }
            case '%' -> {
                if (b == 0) throw new Exception("Modulus by zero is undefined.");
                yield a % b;
            }
            case '^' -> Math.pow(a, b);
            default -> throw new Exception("Unknown operator: " + op);
        };
    }

    // Method to check if a character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    // Optimized precedence check following BODMAS rules
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) return false;
        return (op1 != '^' || op2 != '^');
    }

    // Method to format the result, showing integer results as integers
    private static String formatResult(double result) {
        return (result == (int) result) ? Integer.toString((int) result) : Double.toString(result);
    }

    // Method to calculate factorial of a number
    private static double factorial(double n) throws Exception {
        if (n < 0) throw new Exception("Factorial of a negative number is undefined.");
        if (n == 0 || n == 1) return 1;

        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}