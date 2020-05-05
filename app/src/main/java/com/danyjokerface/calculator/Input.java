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

    // stringNumberInsert storage value of button numeric pressed
    public void appendNumberForTextView(int number_to_insert) {
        if (!stringToInsertOperations.equals("")) {
            buildStringToValue = new StringBuilder();
            stringToInsertOperations = "";
        }

        stringNumberInsert = buildStringToValue.append(number_to_insert).toString();

        if (result != 0.0) {
            operations.setText("");
            result = 0.0;
        }

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

//        if (setOperation.contains(",")) {
//            previousValue = Double.parseDouble(previousValue + "" + number_to_insert);
//        } else {
////            operations.append( previousValue + ","number_to_insert.toString());
//        }

    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public String insertOperator(char operator) {
        if (Input.operator == 'n' && previousValue == 0.0 && result == 0.0 && resultTemp == 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertFirstOperator(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        } else if (resultTemp != 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertMoreOperator(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        if (result != 0.0 && !stringNumberInsert.contains(".")) {
            operatorAfterEqual(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }
        if (value == 0.0 && !stringNumberInsert.contains(".")) {
            changeOperatorOnTheFly(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        if (stringNumberInsert.contains(".")) {
            operatorWhitComma(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
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

    public void operatorWhitComma(char operator) {
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
                // if il risultato ha una virgola
                // =   return numberOfDecimal.format(result);
                int resultToInt = result.intValue();
                return Integer.toString(resultToInt);
            } else {
                buildStringToValue = new StringBuilder();
                result = resultTemp;
                resultTemp = 0.0;
                stringToInsertOperations = "";
                stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(result)).toString();
                String writeRemainder = "\n" + remainderInsert + " " + numberOfDecimal.format(remainder);
                remainder = 0.0;
                resultTemp = 0.0;
                value = 0.0;
                stringNumberInsert = "";
                operator = 'n';
                return stringToInsertOperations + writeRemainder;
            }

        }

        return null;
    }

    // ------------------------Functional---------------------------------------------------------------------------------------------------

    public void resetAll() {
        stringNumberInsert = "";
        previousValue = 0.0;
        value = 0.0;
        resultTemp = 0.0;
        result = 0.0;
        remainder = 0.0;
        operator = 'n';
        buildStringToValue = new StringBuilder();
    }

    public String deleteSet() {
        //add filter +/-  and comma
        if (!stringNumberInsert.equals("") && result == 0.0 && operator == 'n') {
            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
            buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
            return stringNumberInsert;
        }

        if (result != 0.0) {
            String resultToString = Double.toString(result);
            stringNumberInsert = resultToString.substring(0, resultToString.length() - 1);
            result = parseDouble(stringNumberInsert);
            //buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
            return stringNumberInsert;
        } // quando ho il resto

        //quando ho un operatore

        // quando ho il simbolo +/-

        //quando ho la virgola


        return null;
    }


//
//        if (!textViewString.isEmpty() && result == null) {
//
//            operations.setText(textViewString.substring(0, textViewString.length() - 1));
//            setOperation = textViewString.substring(0, textViewString.length() - 1);
//            stringNumberInsert = setOperation;
//        }
//
//        if (!stringNumberInsert.equals("") && result == null) {
//
//            operations.setText(textViewString.substring(0, textViewString.length() - 1));
//            setOperation = textViewString.substring(0, textViewString.length() - 1);
//            stringNumberInsert = setOperation;
//        }
//
//        if (!textViewString.equals("")) {
//            final boolean equals = textViewString.substring(0, textViewString.length() - 1).equals("");
//            if (result != null && !equals) {
//                operations.setText(textViewString.substring(0, textViewString.length() - 1));
//                setOperation = textViewString.substring(0, textViewString.length() - 1);
//                result = Double.parseDouble(setOperation);
//            }
//            if (equals) {
//                operations.setText("");
//                setOperation = "";
//                result = null;
//            }
//
//        }


    public String commaSet() {
        if (!stringNumberInsert.equals("") && operator == 'n' && resultTemp == 0.0 || result != 0.0) {
            buildStringToValue = new StringBuilder();
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert))).append(".").toString();
            return stringNumberInsert.replace(".", ",");
        }

        if (resultTemp != 0.0) {
            buildStringToValue = new StringBuilder();//delete
//todo
//            buildStringToValue = new StringBuilder();
//            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert)) + ".").toString();
//            String stringToView = stringNumberInsert.replace(".", ",");
//            return stringToView;


//            return stringToInsertOperations;
        }
        return null;
    }
}