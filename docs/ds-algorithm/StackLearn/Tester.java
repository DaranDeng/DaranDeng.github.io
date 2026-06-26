package StackLearn;
import java.util.Scanner;
// Define test class
public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RPNCalculator calculator = new RPNCalculator();
        while (true) {
            System.out.println("1. Show instructions");
            System.out.println("2. Run calculator");
            System.out.println("3. Run automated tests");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter a postfix equation of tokens (numbers and operators) separated by commas.");
                    System.out.println("For example: 3, 5, *, 6, 4, +, 2, *, +");
                    break;
                case 2:
                    System.out.println("Enter the postfix expression: ");
                    String expression = scanner.nextLine();
                    double result = calculator.calculate(expression, ",");
                    String infix= calculator.toInfix(expression,",");
                    System.out.println("Infix version: "+infix);
                    System.out.println("Result: " + result);
                    break;
                case 3:
                    stackTest();
                    rpnTest(calculator);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Automated test method for stack
    public static void stackTest() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
    }

    // Automated test method for RPN
    public static void rpnTest(RPNCalculator calculator) {
        String testExpression = "4,2,+";
        double result = calculator.calculate(testExpression, ",");
        System.out.println("Test expression: " + testExpression + ", Result: " + result);
    }
}
class Stack<T> {
    private List<T> list;

    // Constructor, initialize stack
    public Stack() {
        this.list = new List<>();
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Push operation
    public void push(T data) {
        list.add(data);
    }

    // Pop operation
    public T pop() {
        return list.deleteFromStart();
    }
}
// Define linked list class
class List<T> {
    private ListNode <T>head;

    // Constructor, initialize list head node as null
    public List() {
        this.head = null;
    }

    // Add node at head of list
    public void add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = head;
        head = newNode;
    }

    // Delete node from head of list
    public T deleteFromStart() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        T data = head.data;
        head = head.next;
        return data;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return head == null;
    }
}

// Define linked list node class
class ListNode <T>{
    T data;
    ListNode<T> next;

    // Constructor, used to initialize node data
    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}

// Define RPN calculator class
class RPNCalculator {
    // Method to calculate RPN expression
    public double calculate(String expression, String delimiter) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(delimiter);
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                if (!isOperator(token)) {
                    throw new IllegalArgumentException("Invalid token: " + token);
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough operands for operator: " + token);
                }
                double operand2 = stack.pop();
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Not enough operands for operator: " + token);
                }
                double operand1 = stack.pop();
                double result = applyOperator(token, operand1, operand2);
                stack.push(result);
            }
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("No result calculated (empty expression)");
        }
        double result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Too many operands in expression");
        }
        return result;
    }

    // Check if string is a number
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }


    // Perform calculation based on operator
    private double applyOperator(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by 0");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public String toInfix(String expression, String delimiter) {
        Stack<String> stack = new Stack<>();
        String[] tokens = expression.split(delimiter);
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(token);
            } else {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String infix = "(" + operand1 + " " + token + " " + operand2 + ")";
                stack.push(infix);
            }
        }
        return stack.pop();
    }
}