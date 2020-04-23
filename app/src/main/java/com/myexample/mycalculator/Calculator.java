package com.myexample.mycalculator;

public class Calculator extends Input implements CalculatorInterface {
    public void calculate() {
        scannerValue();

        while (!getOperation().equals("=")) {
            scannerOperation();
        }

    }

}