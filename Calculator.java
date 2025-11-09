
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

     }

