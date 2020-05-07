package com.danyjokerface.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class Input extends HomeScreen {
    private static String stringNumberInsert = "";
    private static Double numberCommaInsert = 0.0;
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

        //if have a comma
        if (numberCommaInsert != 0.0) {
            stringNumberInsert = buildStringToValue.append(".").append(numberOfDecimal.format(number_to_insert)).toString();
            numberCommaInsert = 0.0;
        } else {
            // stringNumberInsert storage value of button numeric pressed
            stringNumberInsert = buildStringToValue.append(number_to_insert).toString();
        }

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

            value = 0.0;
        }

        //if insert number after press equal
        if (result != 0.0) {
            buildStringToValue = new StringBuilder();
            stringToInsertOperations = "";
            result = 0.0;
            return stringNumberInsert = buildStringToValue.append(number_to_insert).toString();
        }

        //if insert number after press operator
        if (!stringToInsertOperations.equals("")) {
            return stringToInsertOperations + stringNumberInsert.replace(".", ",");
        }

        // if have a comma on number
        if (stringNumberInsert.contains(".")) {
            return stringNumberInsert.replace(".", ",");
        }

        // other cases
        else {
            return stringNumberInsert;
        }

    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public String insertOperator(char operator) {

        //first operator
        if (Input.operator == 'n' && previousValue == 0.0 && result == 0.0 && resultTemp == 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(",")) {
            insertFirstOperator(operator);
            stringToInsertOperations = numberOfDecimal.format(previousValue) + operator;
            return stringToInsertOperations;
        }

        //more operator
        else if (resultTemp != 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertMoreOperator(operator);
            stringToInsertOperations = numberOfDecimal.format(previousValue) + operator;
            return stringToInsertOperations;
        }

        //operator after equal
        if (result != 0.0) {
            operatorAfterEqual(operator);
            stringToInsertOperations = numberOfDecimal.format(previousValue) + operator;
            return stringToInsertOperations;
        }

        //change operator on the fly
        if (value == 0.0 && !stringNumberInsert.contains(",")) {
            changeOperatorOnTheFly(operator);
            stringToInsertOperations = numberOfDecimal.format(previousValue) + operator;
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

//    public void operatorWhitComma(char operator) {
//        stringNumberInsert = stringNumberInsert.replace(",", ".");
//        previousValue = Double.parseDouble(stringNumberInsert);
//        stringNumberInsert = "";
//        buildStringToValue = new StringBuilder();
//        Input.operator = operator;
//    }

    // ------------------------Equal--------------------------------------------------------------------------------------------------------

    public String equal(String remainderInsert) {
        if (resultTemp != 0.0) {
            if (remainder == 0.0) {
                result = resultTemp;
                resultTemp = 0.0;
                previousValue = 0.0;
                value = 0.0;
                buildStringToValue = new StringBuilder();
                stringNumberInsert = "";
                stringToInsertOperations = "";
                operator = 'n';

//                //if have a comma
//                if ((result.toString()).contains(".") && !(result.toString()).contains(".0")) {
//                    return (result.toString()).replace(".", ",");
//                }

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
                previousValue = 0.0;
                value = 0.0;
                stringNumberInsert = "";
                operator = 'n';
                return writeResult + writeRemainder;
            }

        } else if (!stringNumberInsert.equals("")) {
            return stringNumberInsert;
        } else if (result != 0.0) {
            return numberOfDecimal.format(result);
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


        return null;
    }


    public String commaSet() {

        //after number
        if (!stringNumberInsert.equals("") && operator == 'n' && resultTemp == 0.0 && !stringNumberInsert.contains(".") && result == 0.0) {
            stringNumberInsert = stringNumberInsert + ".";
            numberCommaInsert = Double.parseDouble(stringNumberInsert);
            return stringNumberInsert.replace(".", ",");
        }

        //if insert comma after operator and no numbers
        if (operator != 'n' && stringNumberInsert.equals("")) {
            return stringToInsertOperations;
        }

        //if comma exist
        if (stringNumberInsert.contains(".")) {
            return stringNumberInsert.replace(".", ",");
        }

        //if insert comma after equals
        if (result != 0.0) {
            int resultToInt = result.intValue();
            return Integer.toString(resultToInt);
        }

        //if textview is empty
        return null;
    }

}