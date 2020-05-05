package com.danyjokerface.calculator;

public class Functional extends HomeScreen {
    static boolean checkclick = false;

    public void setToolbar() {
    }

    public String setTextViewToolbar(String greeting, String appname) {
        if (!checkclick) {
            checkclick = true;
            return (greeting + " " + new String(Character.toChars(0x1F609)));
        } else {
            checkclick = false;
            return (appname);
        }

    }

    public void setOperations() {
    }

}
//
//    //
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

//}