package com.example.libmyparser;

public class SimpleParser {

    private int currentIndex;
    private int size;
    private String expression;

    public double parse(String s) throws Exception {
        expression = s;
        size = s.length();
        currentIndex = 0;
        return AddSub();
    }


    private double AddSub() throws Exception {
        double currentRes = MulDiv();
        double addSubRes = currentRes;
        char sign;
        while (currentIndex < size) {
            sign = expression.charAt(currentIndex);
            if (sign != '+' && sign != '-') break;
            currentIndex++;
            currentRes = MulDiv();
            if (sign == '+') {
                addSubRes += currentRes;
            } else {
                addSubRes -= currentRes;
            }
        }
        return addSubRes;
    }

    private double MulDiv() throws Exception {
        double currentRes = Bracket();
        double MulDivRes = currentRes;
        while (true) {
            if (currentIndex >= size) {
                return currentRes;
            }
            char sign = expression.charAt(currentIndex);
            if (sign != '*' && sign != '/') return currentRes;
            currentIndex++;
            double right = Bracket();
            if (sign == '*') {
                MulDivRes *= right;
            } else {
                MulDivRes /= right;
            }
            currentRes = MulDivRes;
        }
    }

    private double Bracket() throws Exception {
        char zeroChar = expression.charAt(currentIndex);
        if (zeroChar == '(') {
            currentIndex++;
            double res = AddSub();
            if (currentIndex < size && expression.charAt(currentIndex) == ')') {
                currentIndex++;
            } else {
                throw new Exception("no closing bracket");
            }
            return res;
        }
        return Negate();
    }


    private double Negate() throws Exception {
        int numberOfMinus = 0;
        while (currentIndex < size && expression.charAt(currentIndex) == '-') {
            currentIndex++;
            numberOfMinus++;
        }
        if (numberOfMinus % 2 != 0) {
            double r = Bracket();
            return (-r);
        }
        return Num();
    }


    private double Num() throws Exception {
        int numberStart = currentIndex;
        while (currentIndex < size && Character.isDigit(expression.charAt(currentIndex))) {
            currentIndex++;
        }
        if (currentIndex == numberStart && (currentIndex >= size || expression.charAt(currentIndex) != '.')) {
            throw new Exception("Missing operand or unknown symbol");
        }
        if (currentIndex < size && expression.charAt(currentIndex) == '.') {
            currentIndex++;
            while (currentIndex < size && Character.isDigit(expression.charAt(currentIndex))) {
                currentIndex++;
            }
        }
        return Double.parseDouble(expression.substring(numberStart, currentIndex));
    }


    /*
    public static void main(String[] args) {
        try {
            System.out.println((new SimpleParser()).parse("(10000)/(2-1)*(10*4/5)+1.8"));
        } catch (Exception e) {
            System.err.println("Error while parsing '" + "' with message: " + e.getMessage());
        }

    }
    */

}