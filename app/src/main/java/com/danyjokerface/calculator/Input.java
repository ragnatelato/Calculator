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
    public String appendNumberForTextView(int number_to_insert) {
        String insertOnTextView = "";

        // delete textview before insert operator and second numbers
        if (!stringToInsertOperations.equals("")) {
            insertOnTextView = buildStringToValue.append(number_to_insert).toString();
            buildStringToValue = new StringBuilder();
            stringToInsertOperations = "";
        }

        //assign number insert
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
            buildStringToValue = new StringBuilder();
            stringToInsertOperations = "";
            result = 0.0;
            return stringNumberInsert = buildStringToValue.append(number_to_insert).toString();
        }

        //if insert number after press operator
        if (!insertOnTextView.equals("")) {
            return insertOnTextView;
        }

        // if have a comma on number
        if ((stringNumberInsert).contains(".")) {
            stringNumberInsert = stringNumberInsert.replace(".", ",");
            return stringNumberInsert;
        }

        // other cases
        else {
            return stringNumberInsert;
        }

    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public String insertOperator(char operator) {

        //first operator
        if (Input.operator == 'n' && previousValue == 0.0 && result == 0.0 && resultTemp == 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertFirstOperator(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();

            //if have a comma
            if ((stringToInsertOperations).contains(".")) {
                return stringToInsertOperations.replace(".", ",");
            }

            return stringToInsertOperations;
        }

        //more operator
        else if (resultTemp != 0.0 && !stringNumberInsert.equals("") && !stringNumberInsert.contains(".")) {
            insertMoreOperator(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        //operator after equal
        if (result != 0.0 && !stringNumberInsert.contains(".")) {
            operatorAfterEqual(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations.replace(".", ",");
        }

        //change operator on the fly
        if (value == 0.0 && !stringNumberInsert.contains(".")) {
            changeOperatorOnTheFly(operator);
            stringToInsertOperations = buildStringToValue.append(numberOfDecimal.format(previousValue)).append(operator).toString();
            return stringToInsertOperations;
        }

        //comma logic
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

                //if have a comma
                if ((result.toString()).contains(".00")) {
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
        previousValue = 0.0;
        value = 0.0;
        resultTemp = 0.0;
        result = 0.0;
        remainder = 0.0;
        operator = 'n';
        buildStringToValue = new StringBuilder();
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


    //    public void onClickPosNeg(View view) {
////        String formatTextView = operations.getText().toString();
////
////        if (stringNumberInsert == null) {
////        }
////
////        String formatString = "";
////        if (formatTextView.equals("") || formatTextView.equals("+")) {
////            formatString = "-";
////            operations.setText(formatString);
////        } else if (formatTextView.equals("-")) {
////            formatString = "+";
////            operations.setText(formatString);
////        } else {
//////            if (formatString.equals("-" + stringNumberInsert)) {
//////                formatString = "" + stringNumberInsert;
//////                operations.setText(formatString);
//////            } else {
//////                formatString = "-" + stringNumberInsert;
//////                operations.setText(formatString);
//////            }
////        }
////
////        if (operator != null) {
////
////        }
////
////        if (result != null) {
////            if (result > 0) {
////                formatString = "-" + result;
////                operations.setText(formatString);
////                result = Double.parseDouble(formatString);
////
////            } else if (result < 0) {
////                formatString = result.toString().replace("-", "+");
////                operations.setText(formatString);
////                result = Double.parseDouble(formatString);
////            }
////
////        }
////
////
//////        previousValue = Double.parseDouble(formatString + stringNumberInsert);
////        //todo
//    }

    public String commaSet() {

        //after number
        if (!stringNumberInsert.equals("") && operator == 'n' && resultTemp == 0.0 && !stringNumberInsert.contains(".")) {
            buildStringToValue = new StringBuilder();
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert))).append(".").toString();
            return stringNumberInsert.replace(".", ",");
        }

        //if insert comma after operator
        if (operator != 'n') {
            return buildStringToValue.toString();
        }

        //if comma exist
        if (stringNumberInsert.contains(".")) {
            return stringNumberInsert;
        }

        //if insert comma after number after operator
        if (resultTemp != 0.0) {
            buildStringToValue = new StringBuilder();
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(parseDouble(stringNumberInsert))).append(".").toString();
            return stringNumberInsert.replace(".", ",");

            //todo
        }

        //if insert comma after euqals
        if (result != 0.0) {
            stringNumberInsert = buildStringToValue.append(numberOfDecimal.format(result)).append(".").toString();
            return stringNumberInsert.replace(".", ",");
        }

        //if textview is empty
        return null;
    }

}