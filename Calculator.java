import java.util.*;

public class Calculator {

    public static String convertToPostfix(String infix) {
        Map<Character,Integer> prec = Map.of('+',1,'-',1,'*',2,'/',2,'^',3);
        Set<Character> rightAssoc = Set.of('^');

        StringBuilder out = new StringBuilder();
        StackInterface<Character> ops = new LinkedStack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (Character.isWhitespace(ch)) continue;

            if (Character.isLetterOrDigit(ch)) {
                out.append(ch).append(' ');
            } else if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    out.append(ops.pop()).append(' ');
                }
                if (ops.isEmpty()) throw new IllegalArgumentException("Mismatched parentheses");
                ops.pop(); // discard '('
            } else if (prec.containsKey(ch)) {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    char top = ops.peek();
                    if (!prec.containsKey(top)) break;
                    boolean leftAssoc = !rightAssoc.contains(ch);
                    int pTop = prec.get(top), pCur = prec.get(ch);
                    if ((leftAssoc && pTop >= pCur) || (!leftAssoc && pTop > pCur)) {
                        out.append(ops.pop()).append(' ');
                    } else break;
                }
                ops.push(ch);
            } else {
                throw new IllegalArgumentException("Unexpected token: " + ch);
            }
        }
        while (!ops.isEmpty()) {
            char op = ops.pop();
            if (op == '(') throw new IllegalArgumentException("Mismatched parentheses");
            out.append(op).append(' ');
        }
        return out.toString().trim();
    }

    private static double lookupValue(char var, char[] vars, double[] vals) {
        if (vars == null || vals == null || vars.length != vals.length) {
            throw new IllegalArgumentException("Invalid variable or value arrays.");
        }
        for (int i = 0; i < vars.length; i++) {
            if (vars[i] == var) return vals[i];
        }
        throw new IllegalArgumentException("No value for variable: " + var);
    }

    private static double apply(char op, double a, double b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            case '^': return Math.pow(a, b);
        }
        throw new IllegalArgumentException("Invalid operator: " + op);
    }

    public static double evaluatePostfix(String postfix, char[] vars, double[] vals) {
        StackInterface<Double> st = new ResizeableArrayStack<>();
        StringTokenizer tok = new StringTokenizer(postfix);
        while (tok.hasMoreTokens()) {
            String t = tok.nextToken();
            char ch = t.charAt(0);
            if (t.length() == 1 && Character.isLetter(ch)) {
                st.push(lookupValue(ch, vars, vals));
            } else if (t.length() == 1 && "+-*/^".indexOf(ch) >= 0) {
                double b = st.pop();
                double a = st.pop();
                st.push(apply(ch, a, b));
            } else if (t.matches("^-?\\d+(\\.\\d+)?$")) {
                st.push(Double.parseDouble(t));
            } else {
                throw new IllegalArgumentException("Bad token: " + t);
            }
        }
        double ans = st.pop();
        if (!st.isEmpty()) throw new IllegalStateException("Extra values left on stack");
        return ans;
    }

    public static void main(String[] args) {
        String infix = "a*b/(c-a)+d*e";
        String postfix = convertToPostfix(infix);
        char[] vars = {'a','b','c','d','e'};
        double[] vals = {2,3,4,5,6};
        double result = evaluatePostfix(postfix, vars, vals);

        System.out.println("Infix Expression:   " + infix);
        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Evaluation Result:  " + result);
    }
}