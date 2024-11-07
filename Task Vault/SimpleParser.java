package com.jethers.reglogwdb; // Your package name

import java.util.Stack;

public class SimpleParser {
    public double evaluate(String expression) {
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                values.push(Double.parseDouble(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    double value2 = values.pop();
                    double value1 = values.pop();
                    String operator = operators.pop();
                    values.push(applyOperator(value1, value2, operator));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            double value2 = values.pop();
            double value1 = values.pop();
            String operator = operators.pop();
            values.push(applyOperator(value1, value2, operator));
        }

        return values.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }

    private double applyOperator(double value1, double value2, String operator) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return value1 / value2;
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + operator);
        }
    }
}
