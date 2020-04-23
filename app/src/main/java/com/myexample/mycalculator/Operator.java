package com.myexample.mycalculator;

/**
 * select operator from list
 */
public class Operator extends Input {
    private static Double remainder = 0.0;

    public static Double getRemainder() {
        return remainder;
    }

    public Double multiplication(Double previousValue, Double value) {
        return previousValue * value;
    }

    public Double division(Double previousValue, Double value) {
        remainder = previousValue % value;
        return previousValue / value;
    }

    public Double sum(Double previousValue, Double value) {
        return previousValue + value;
    }

    public Double subtraction(Double previousValue, Double value) {
        return previousValue - value;
    }

}