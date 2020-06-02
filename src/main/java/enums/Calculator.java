package enums;

import java.util.function.DoubleBinaryOperator;

public class Calculator {
    public enum Calculate {
        ADD ((x, y) -> (x + y)),
        SUBTRACT ((x, y) -> ( x - y)),
        MULTIPLY ( (x, y) -> ( x * y)) ,
        DIVIDE ((x, y) -> ( x / y)),
        POWER (Math::pow);

        private final DoubleBinaryOperator function;

        Calculate(DoubleBinaryOperator function) {
            this.function = function;
        }

        public DoubleBinaryOperator getFunction() {
            return function;
        }
    }

    public static double calc(double x, double y, Calculate operation) {
        return operation.getFunction().applyAsDouble(x, y);
    }
    public static void main(String[] args) {
        double a = 2;
        double b = 6;
        double c = calc(a, b, Calculate.POWER);
        double d = calc(c, b, Calculate.MULTIPLY);
        System.out.println(calc(d, a, Calculate.DIVIDE));

    }
}