public class CalculatorTest {
    public static void main(String[] args) {
        String infix = "a*b/(c-a)+d*e";
        String postfix = Calculator.convertToPostfix(infix);
        char[] vars = {'a','b','c','d','e'};
        double[] vals = {2,3,4,5,6};
        double result = Calculator.evaluatePostfix(postfix, vars, vals);

        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + result);

        if (postfix.equals("a b * c a - / d e * +") && Math.abs(result - 33.0) < 0.001)
            System.out.println("Test passed");
        else
            System.out.println("Test failed");
    }
}