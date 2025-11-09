
public class Calculator {
    /** All methods for converting and evaluating expressions are in here
     * @param postfix The postfix expression to evaluate
     * @param vars    The variables from the provided expression 
     * @param vals    The values provided for the project 
     * @return The result of the evaluation
     */

     public static String convertToPostFix(String infix) {
        StackInterface<Character> operatorStack = new ResizeableArrayStack<>(); //Creates an empty stack for operators
        StringBuilder postfix = new StringBuilder(); //Creates an empty string for the postfix expression 
        
        /*
         * This loop follows the precedence algorithm
         * It makes sure that the parentheses are handled correctly
         * It also ensures that the operators are placed in the correct order based on their precedence
         */

        for (int i = 0; i < infix.length(); i++) { 
            char ch = infix.charAt(i); 
            if (Character.isLetter(ch)) {
                postfix.append(ch);
            }
            else {
                switch (ch) {
                    case '^': //Highest precedence 
                        operatorStack.push(ch);
                        break;
                    case '*': case '/': case '+': case '-': //Equal precedence but depends on the order of its appearance
                        while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(ch)) {
                            postfix.append(operatorStack.pop());
                        }
                        operatorStack.push(ch);
                        break;
                    case '(': //Lowest precedence always push onto the stack 
                        operatorStack.push(ch);
                        break;
                    case ')': //Pop until the corresponding '(' is found
                        char top = operatorStack.pop();
                        while (top != '(') {
                            postfix.append(top);
                            top = operatorStack.pop();
                        }
                        break; 
                    default:
                        break;
                    }
                }
            }
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }
        return postfix.toString();
    }
    /**
     * LookupValue method is to find the variables value, i.e. a = 2
     * @param var
     * @param vars
     * @param vals
     * @return
     */
        private static double lookupValue(char var, char[] vars, double[] vals) {
        for (int i = 0; i < vars.length; i++) {
            if (vars[i] == var) {
                return vals[i];
            }
        }
    }
    /*
     * Precedence method to determine the precedence of operators
     */
        private static int precedence(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*': case '/':
                return 2;
            case '+': case '-':
                return 1;
            case '(':
                return 0;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    /*
     * This method applies the operator to the two operands
     */
    private static double apply(char op, double a, double b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    
        public static double evaluatePostFix(String postfix, char[] vars, double[] vals) {
        StackInterface<Double> valueStack = new ResizeableArrayStack<>(); //Creates an empty stack for values

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (Character.isLetter(ch)) {
                valueStack.push(lookupValue(ch, vars, vals)); //Pushes the value of the variable onto the stack
            } else {
                switch (ch) {
                    case '*': case '/': case '+': case '-': case '^': 
                        double operandTwo = valueStack.pop();
                        double operandOne = valueStack.pop(); //Pops A and B and performs the arithmetic operation
                        double result = apply(ch, operandOne, operandTwo);
                        valueStack.push(result);
                        break;
                        default:
                        break;    
                }
        }
        return valueStack.pop();
    }
}
}

