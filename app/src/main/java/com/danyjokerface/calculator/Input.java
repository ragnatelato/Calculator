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

            //if number whit comma start whit 0
//            if (numberCommaInsert.toString().startsWith("0.")) {
//                int resultToInt = result.intValue();
//                //if have a comma
//                if (resultToInt != result) {
//                    return (result.toString()).replace(".", ",");
//                }
//            }

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
            return stringToInsertOperations.replace(".", ",") + stringNumberInsert.replace(".", ",");
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
            return stringToInsertOperations.replace(".", ",");
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
            return stringToInsertOperations.replace(".", ",");
        }

        //change operator on the fly
        if (value == 0.0 && !stringNumberInsert.contains(",")) {
            changeOperatorOnTheFly(operator);
            stringToInsertOperations = numberOfDecimal.format(previousValue) + operator;
            return stringToInsertOperations.replace(".", ",");
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

                int resultToInt = result.intValue();
                //if have a comma
                if (resultToInt != result) {
                    return (result.toString()).replace(".", ",");
                }
                // if not have
                else {
                    return Integer.toString(resultToInt);
                }

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
            return stringNumberInsert.replace(".", ",");
        }

        //quando ho la virgola
        if (stringNumberInsert.contains(".#")) {
            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
            buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
            return stringNumberInsert.replace(".", ",");
        }

        //quando ho un operatore
        if (operator != 'n') {
            return stringToInsertOperations;
        }

        // delete on a result
        if (result != 0.0) {

            int resultToInt = result.intValue();
            //if don't have a comma
            if (result.toString().contains(".0")) {
                return Integer.toString(resultToInt);
            }
            // if have
            else {
                return result.toString().replace(".", ",");
            }

        }

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

        //todo


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

        //if have a more numbers whit comma
        if (!stringToInsertOperations.equals("") && !stringNumberInsert.equals("")) {
            stringNumberInsert = stringNumberInsert + ".";
            numberCommaInsert = Double.parseDouble(stringNumberInsert);
            return stringToInsertOperations.replace(".", ",") + stringNumberInsert.replace(".", ",");
        }

        //if textview is empty
        return null;
    }

}