package com.myexample.mycalculator;

import android.annotation.SuppressLint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Input {
    private static Double value = 0.0;
    private final Scanner scanner = new Scanner(System.in);
    private final File file = new File("C:/Users/Dany/IdeaProjects/MyExercises/Calculator/CalculatorLog.txt");
    private String operation = "";

    static Double getValue() {
        return value;
    }

    /**
     * Scanner value from input
     *
     * @return value from user
     */
    public Double scannerValue() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            System.out.print("insert a number: ");
            value = scanner.nextDouble();
            //System.out.println("you have insert: " + value);
            bufferedWriter.write(("you have insert: " + value));
            bufferedWriter.close();
        } catch (InputMismatchException | ArithmeticException exception) {
            System.err.println("not a number or incorrect format \n(the decimal required ',')");
            System.exit(1);
        } catch (IOException ioex) {
            System.out.println("Error on file");
            System.exit(1);
        }

        return value;
    }

    /**
     * Scanner operator from input
     */
    @SuppressLint("DefaultLocale")
    public void scannerOperation() {
        System.out.print("now insert a operator type: ");
        Operator operator = new Operator();
        operation = scanner.next().toUpperCase();

        if (operation.equals("X")) {
            operation = "*";
        }

        switch (operation) {
            case "+":
                value = operator.sum(getValue(), scannerValue());
                break;
            case ("-"):
                value = operator.subtraction(getValue(), scannerValue());
                break;
            case ("/"):
                value = operator.division(getValue(), scannerValue());
                break;
            case ("*"):
                value = operator.multiplication(getValue(), scannerValue());
                break;
            case ("="): {
                System.out.println("The result is: " + String.format("%.2f", getValue()));
                System.out.println("the remainder is: " + String.format("%.2f", Operator.getRemainder()));
                System.err.println("Calcolatrice fatta da: \nDANY-DanyJokerFace");
                scanner.reset().close();
                break;
            }
            default: {
                System.err.println("Not a operator");
                System.exit(1);
                break;
            }
        }

    }

    public String getOperation() {
        return operation;
    }

}