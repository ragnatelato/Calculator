package com.danyjokerface.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class Input extends HomeScreen {
    private static String stringNumberInsert = "";
    private static String stringToInsertOperations = "";
    private static Double previousValue = 0.0;
    private static Double value = 0.0;
    private static Double resultTemp = 0.0;
    private static Double result = 0.0;
    private static Double remainder = 0.0;
    private static char operator = 'n';
    DecimalFormatSymbols decimalFormat = new DecimalFormatSymbols(Locale.US);
    DecimalFormat numberOfDecimal = new DecimalFormat("#.##", decimalFormat);
    StringBuilder buildStringToValue = new StringBuilder();

    // ------------------------Insert Numbers-----------------------------------------------------------------------------------------------

    public String appendNumberForTextView(int number_to_insert) {
        String insertOnTextView = "";

        // delete textview before insert operator and second numbers
//        if (!stringToInsertOperations.equals("")) {
//            insertOnTextView = buildStringToValue.append(number_to_insert).toString();
//            buildStringToValue = new StringBuilder();
//        }

        // stringNumberInsert storage value of button numeric pressed
        stringNumberInsert = buildStringToValue.append(number_to_insert).toString();

        // execute operations
        if (operator != 'n') {
            switch (operator) {
                case ('%'):
                    value = parseDouble(stringNumberInsert);
                    resultTemp = (previousValue * value) / 100;
                    break;

                case ('/'):
                    value = parseDouble(stringNumberInsert);
                    resultTemp = previousValue / value;
                    remainder = previousValue % value;
                    break;

                case ('*'):
                    value = parseDouble(stringNumberInsert);
                    resultTemp = previousValue * value;
                    break;

                case ('-'):
                    value = parseDouble(stringNumberInsert);
                    resultTemp = previousValue - value;
                    break;

                case ('+'):
                    value = parseDouble(stringNumberInsert);
                    resultTemp = previousValue + value;
                    break;
            }

            operator = 'n';
            previousValue = 0.0;
            value = 0.0;
        }

        //if insert number after press equal
        if (result != 0.0) {

            // if insert a comma
//            if (stringNumberInsert.contains(".")) {
//                result = 0.0;
//                return (stringNumberInsert).replace(".", ",");
//            }

            buildStringToValue = new StringBuilder();
            stringToInsertOperations = "";
            result = 0.0;
            return stringNumberInsert = buildStringToValue.append(number_to_insert).toString();
        }

        //if insert number after press operator
        if (!insertOnTextView.equals("")) {

            //if i have a comma
            if (insertOnTextView.contains(".")) {
                insertOnTextView = insertOnTextView.replace(".", ",");
                return insertOnTextView;
            }

            return insertOnTextView;
        }

        // if have a comma on number
        if ((stringNumberInsert).contains(".")) {
            stringNumberInsert = stringNumberInsert.replace(".", ",");
            return stringNumberInsert;
        }

        // other cases
        else {
            return stringToInsertOperations + stringNumberInsert;
        }

    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public String insertOperator(char operator) {

        //first operator
        if (Input.operator == 'n' && previousValue == 0.0 && result == 0.0 && resultTemp == 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(",")) {
            insertFirstOperator(operator);

//            // if i have a comma
//            if ((previousValue.toString()).contains(".") && !(previousValue.toString()).contains(".0")) {
//                return (previousValue.toString()).replace(".", ",") + operator;
//            }

            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            buildStringToValue = new StringBuilder();
            return stringToInsertOperations;
        }

        //more operator
        else if (resultTemp != 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertMoreOperator(operator);

            // if i have a comma
            if ((previousValue.toString()).contains(".") && !(previousValue.toString()).contains(".0")) {
                return (previousValue.toString()).replace(".", ",") + operator;
            }

            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        //operator after equal
        if (result != 0.0) {

            // if i have comma
            if (stringNumberInsert.contains(".")) {
                return stringNumberInsert.replace(".", ",") + operator;
            }

            operatorAfterEqual(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations.replace(".", ",");
        }

        //change operator on the fly
        if (value == 0.0 && !stringNumberInsert.contains(",")) {
            changeOperatorOnTheFly(operator);

            // if i have a comma
            if ((previousValue.toString()).contains(".") && !(previousValue.toString()).contains(".0")) {
                return (previousValue.toString()).replace(".", ",") + operator;
            }

            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        //comma logic
        if (stringNumberInsert.contains(",")) {
            operatorWhitComma(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            stringToInsertOperations = stringToInsertOperations.replace(".", ",");
            return stringToInsertOperations;
        }

        return null;
    }

    // ------------------------Conditions For Operators-------------------------------------------------------------------------------------

    public void insertFirstOperator(char operator) {
        buildStringToValue = new StringBuilder();
        previousValue = parseDouble(stringNumberInsert);
        stringNumberInsert = "";
        Input.operator = operator;
    }

    public void insertMoreOperator(char operator) {
        previousValue = resultTemp;
        resultTemp = 0.0;
        stringNumberInsert = "";
        buildStringToValue = new StringBuilder();
        Input.operator = operator;
    }

    public void operatorAfterEqual(char operator) {
        previousValue = result;
        result = 0.0;
        stringNumberInsert = "";
        buildStringToValue = new StringBuilder();
        Input.operator = operator;
    }

    public void changeOperatorOnTheFly(char operator) {
        stringNumberInsert = "";
        buildStringToValue = new StringBuilder();
        Input.operator = operator;
    }

    public void operatorWhitComma(char operator) {
        stringNumberInsert = stringNumberInsert.replace(",", ".");
        previousValue = Double.parseDouble(stringNumberInsert);
        stringNumberInsert = "";
        buildStringToValue = new StringBuilder();
        Input.operator = operator;
    }

    // ------------------------Equal--------------------------------------------------------------------------------------------------------

    public String equal(String remainderInsert) {
        if (resultTemp != 0.0) {
            if (remainder == 0.0) {
                result = resultTemp;
                resultTemp = 0.0;
                value = 0.0;
                buildStringToValue = new StringBuilder();
                stringNumberInsert = "";
                stringToInsertOperations = "";
                operator = 'n';

                //if have a comma
                if ((result.toString()).contains(".") && !(result.toString()).contains(".0")) {
                    return (result.toString()).replace(".", ",");
                }

                // if not have
                int resultToInt = result.intValue();
                return Integer.toString(resultToInt);
            }

            //division/remainder logic for equal
            else {
                buildStringToValue = new StringBuilder();
                result = resultTemp;
                resultTemp = 0.0;
                stringToInsertOperations = "";
                stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(result)).toString();
                String writeResult = stringToInsertOperations.replace(".", ",");
                String writeRemainder = "\n" + remainderInsert + " " + numberOfDecimal.format(remainder);
                remainder = 0.0;
                resultTemp = 0.0;
                value = 0.0;
                stringNumberInsert = "";
                operator = 'n';
                return writeResult + writeRemainder;
            }

        }

        return null;
    }

    // ------------------------Functional---------------------------------------------------------------------------------------------------

    public void resetAll() {
        stringNumberInsert = "";
        stringToInsertOperations = "";
        previousValue = 0.0;
        value = 0.0;
        resultTemp = 0.0;
        result = 0.0;
        remainder = 0.0;
        operator = 'n';
        buildStringToValue = new StringBuilder();
        decimalFormat = new DecimalFormatSymbols(Locale.US);
        numberOfDecimal = new DecimalFormat("#.##", decimalFormat);
    }

    public String deleteSet() {

        // delete on a number
        if (!stringNumberInsert.equals("") && result == 0.0 && operator == 'n') {
            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
            buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
            return stringNumberInsert;
        }

        // delete on a result
        if (result != 0.0) {
            String resultToString = Double.toString(result);
            stringNumberInsert = resultToString.substring(0, resultToString.length() - 1);
            result = parseDouble(stringNumberInsert);
            //buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
            return stringNumberInsert;
        }

        // quando ho il resto

        //quando ho un operatore

        // quando ho il simbolo +/-

        //quando ho la virgola

        return null;
    }

    public String posNegSet() {
        //before number
//        if (!stringNumberInsert.equals("") && operator == 'n' && resultTemp == 0.0 && !stringNumberInsert.contains(",")) {
//
//        }

        //if insert posneg after operator
//        if (operator != 'n') {
//
//        }

        //if posneg exist
//        if (stringNumberInsert.contains(",")) {
//
//        }

        //if insert posneg after number after operator
//        if (resultTemp != 0.0) {
//        }

        return null;
    }


    public String commaSet() {

        //after number
        if (!stringNumberInsert.equals("") && operator == 'n' && resultTemp == 0.0 && !stringNumberInsert.contains(",")) {
            buildStringToValue = new StringBuilder();
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert))).append(".").toString();
            return stringNumberInsert.replace(".", ",");
        }

        //if insert comma after operator
        if (operator != 'n') {
            return stringToInsertOperations;
        }

        //if comma exist
        if (stringNumberInsert.contains(",")) {
            return stringNumberInsert;
        }

        //if insert comma after number after operator
        if (resultTemp != 0.0) {
            buildStringToValue = new StringBuilder();

            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert))).append(".").toString();
//            return stringNumberInsert.replace(".", ",");

            return stringNumberInsert.replace(".", ",");
            //todo
        }

        //if insert comma after equals
        if (result != 0.0) {
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(result)).append(".").toString();
            stringToInsertOperations = "";
            return stringNumberInsert.replace(".", ",");
        }

        //if textview is empty
        return null;
    }

}