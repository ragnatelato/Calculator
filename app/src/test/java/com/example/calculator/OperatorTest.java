package com.example.calculator;

import org.junit.Test;

import mycalculator.Operator;

import static junit.framework.TestCase.assertEquals;

public class OperatorTest {
    static Double firstValue = 100.9;
    static Double secondValue = 2.1;
    Double remainder = Operator.getRemainder();
    Operator operator = new Operator();

    @Test
    public void multiplication() {
        assertEquals(firstValue * secondValue, operator.multiplication(firstValue, secondValue));
    }

    @Test
    public void division() {
        assertEquals(firstValue / secondValue, operator.division(firstValue, secondValue), remainder);
    }

    @Test
    public void sum() {
        assertEquals(firstValue + secondValue, operator.sum(firstValue, secondValue));
    }

    @Test
    public void subtraction() {
        assertEquals(firstValue - secondValue, operator.subtraction(firstValue, secondValue));
    }

}