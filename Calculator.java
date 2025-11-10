import java.util.*;

public class Calculator {

    // Task 2: Convert infix → postfix using LinkedStack
    public static String convertToPostfix(String infix) {
        Map<Character, Integer> prec = Map.of('+', 1, '-', 1, '*', 2, '/', 2, '^', 3); // Highest number is the highest precedence
        StringBuilder out = new StringBuilder();
        StackInterface<Character> ops = new LinkedStack<>();

        for (char ch : infix.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;

            if (Character.isLetter(ch)) {
                out.append(ch).append(' ');
            } else if (ch == '(') {
                ops.push(ch); // Open parenthesis overrides the rules of operator precedence
            } else if (ch == ')') {
                while (!ops.isEmpty() && ops.peek() != '(')
                    out.append(ops.pop()).append(' ');
                ops.pop(); // remove '(' and pop all operators until '('
            } else if (prec.containsKey(ch)) {
                while (!ops.isEmpty() && ops.peek() != '(' &&
                        prec.get(ops.peek()) >= prec.get(ch))
                    out.append(ops.pop()).append(' ');
                ops.push(ch); // Depending on precedence and associativity, pop operators from stack to output 
            }
        }
        while (!ops.isEmpty()) out.append(ops.pop()).append(' ');
        return out.toString().trim(); // Add remaining operators to output 
    }

    // Task 5: Evaluate postfix using ResizeableArrayStack
    public static double evaluatePostfix(String postfix, char[] vars, double[] vals) {
        StackInterface<Double> st = new ResizeableArrayStack<>();
        Map<Character, Double> map = new HashMap<>();
        for (int i = 0; i < vars.length; i++) map.put(vars[i], vals[i]); // Finds the value of each variable 

        for (String token : postfix.split(" ")) { // Split by space 
            char ch = token.charAt(0);
            if (Character.isLetter(ch)) { // if it's a letter, push its value onto the stack
                st.push(map.get(ch));
            } else {
                double b = st.pop();
                double a = st.pop(); // If not a letter, it's an operator; pop two values and apply the operator
                switch (ch) {
                    case '+': st.push(a + b); break;
                    case '-': st.push(a - b); break;
                    case '*': st.push(a * b); break;
                    case '/': st.push(a / b); break;
                }
            }
        }
        return st.pop();
    }

    // Demo (Tasks 3 & 6)
    public static void main(String[] args) {
        String infix = "a*b/(c-a)+d*e";
        String postfix = convertToPostfix(infix); // Convert infix expression to postfix notation
        char[] vars = {'a', 'b', 'c', 'd', 'e'};
        double[] vals = {2, 3, 4, 5, 6};
        double result = evaluatePostfix(postfix, vars, vals); // Evaluate the postfix expression with given variable values

        System.out.println("Infix:   " + infix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Result:  " + result);
    }
}