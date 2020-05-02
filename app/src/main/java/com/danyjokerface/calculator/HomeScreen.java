package com.danyjokerface.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class HomeScreen extends AppCompatActivity {
    private static int count = 0;
    private static Double previousValue = null;
    private static StringBuilder buildStringToValue;
    private static Double value = null;
    private static Double remainder = null;
    private static String operator = null;
    private static Double resultTemp = null;
    private static Double result = null;
    private static Boolean equalPass = false;
    private static String setOperation = "";
    private static boolean commaValue = false;
    DecimalFormatSymbols decimalFormatSymbols;
    DecimalFormat numberOfDecimal;
    boolean checkclick = false;
    private String stringNumberInsert = "";
    private TextView operations;
    private TextView textViewToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        operations = findViewById(R.id.operations);
        textViewToolbar = findViewById(R.id.textViewToolbar);
        TextView author = findViewById(R.id.author);
        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        author.startAnimation(fade_in);
        buildStringToValue = new StringBuilder();
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        numberOfDecimal = new DecimalFormat("#.##########", decimalFormatSymbols);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    // ------------------------Insert Number------------------------------------------------------------------------------------------------

    public void insertNumberOnTextView(Integer number_to_insert) {
        stringNumberInsert = buildStringToValue.append(number_to_insert).toString();

        if (result != null && equalPass) {
            operations.setText("");
            result = null;
            equalPass = false;
        }

        if (!equalPass) {
            result = null;
            equalPass = false;
        }

        if (operator != null) {
            switch (operator) {
                case ("%"):
                    value = Double.parseDouble(stringNumberInsert);
                    resultTemp = (previousValue * value) / 100;
                    break;

                case ("/"):
                    value = Double.parseDouble(stringNumberInsert);
                    resultTemp = previousValue / value;
                    remainder = previousValue % value;
                    break;

                case ("*"):
                    value = Double.parseDouble(stringNumberInsert);
                    resultTemp = previousValue * value;
                    break;

                case ("-"):
                    value = Double.parseDouble(stringNumberInsert);
                    resultTemp = previousValue - value;
                    break;

                case ("+"):
                    value = Double.parseDouble(stringNumberInsert);
                    resultTemp = previousValue + value;
                    break;
            }
        }

        if (setOperation.contains(",")) {
            previousValue = Double.parseDouble(previousValue + "" + number_to_insert);
        }

        operations.append(number_to_insert.toString());
    }

    public void onClickNine(View view) {
        insertNumberOnTextView(9);
    }

    public void onClickEight(View view) {
        insertNumberOnTextView(8);
    }

    public void onClickSeven(View view) {
        insertNumberOnTextView(7);
    }

    public void onClickSix(View view) {
        insertNumberOnTextView(6);
    }

    public void onClickFive(View view) {
        insertNumberOnTextView(5);
    }

    public void onClickFour(View view) {
        insertNumberOnTextView(4);
    }

    public void onClickThree(View view) {
        insertNumberOnTextView(3);
    }

    public void onClickTwo(View view) {
        insertNumberOnTextView(2);
    }

    public void onClickOne(View view) {
        insertNumberOnTextView(1);
    }

    public void onClickZero(View view) {
        insertNumberOnTextView(0);
    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public void changeOperatorOnTheFly(String operator) {
        String TempResultView = numberOfDecimal.format(previousValue) + operator;
        operations.setText(TempResultView);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void insertFirstOperator(String operator) {
        previousValue = Double.parseDouble(stringNumberInsert);
        operations.append(operator);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void insertMoreOperator(String operator) {
        HomeScreen.operator = null;
        previousValue = resultTemp;
        String TempResultView = numberOfDecimal.format(resultTemp) + operator;
        operations.setText(TempResultView);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void operatorAfterEqual(String operator) {
        String TempResultView = numberOfDecimal.format(result) + operator;
        operations.setText(TempResultView);
        previousValue = result;
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
        equalPass = false;
    }


    public void onClickPercentage(View view) {
        if (value == null && operator != null && !operations.getText().toString().isEmpty()) {
            changeOperatorOnTheFly("%");
        }

        if (operator == null && previousValue == null && !equalPass && !operations.getText().toString().isEmpty()) {
            insertFirstOperator("%");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("%");
        }

        if (result != null) {
            operatorAfterEqual("%");
        }

    }

    public void onClickDivision(View view) {
        if (value == null && operator != null && !operations.getText().toString().isEmpty()) {
            changeOperatorOnTheFly("/");
        }

        if (operator == null && previousValue == null && !equalPass && !operations.getText().toString().isEmpty()) {
            insertFirstOperator("/");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("/");
        }

        if (result != null) {
            operatorAfterEqual("/");
        }

    }

    public void onClickMultiplication(View view) {
        if (value == null && operator != null && !operations.getText().toString().isEmpty()) {
            changeOperatorOnTheFly("*");
        }

        if (operator == null && previousValue == null && !equalPass && !operations.getText().toString().isEmpty()) {
            insertFirstOperator("*");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("*");
        }

        if (result != null) {
            operatorAfterEqual("*");
        }

    }

    public void onClickSubtraction(View view) {
        if (value == null && operator != null && !operations.getText().toString().isEmpty()) {
            changeOperatorOnTheFly("-");
        }

        if (operator == null && previousValue == null && !equalPass && !operations.getText().toString().isEmpty()) {
            insertFirstOperator("-");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("-");
        }

        if (result != null) {
            operatorAfterEqual("-");
        }

    }

    public void onClickSum(View view) {
        if (value == null && operator != null && !operations.getText().toString().isEmpty()) {
            changeOperatorOnTheFly("+");
        }

        if (operator == null && previousValue == null && !equalPass && !operations.getText().toString().isEmpty()) {
            insertFirstOperator("+");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("+");
        }

        if (result != null) {
            operatorAfterEqual("+");
        }

        if (commaValue)
        {
            insertFirstOperator("+");
        }

    }

    public void onClickEqual(View view) {
        if (resultTemp != null) {
            if (!equalPass && remainder == null && !operations.getText().toString().isEmpty()) {
                result = resultTemp;
                operations.setText(numberOfDecimal.format(result));
                stringNumberInsert = "";
                buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
                previousValue = null;
                value = null;
                resultTemp = null;
                operator = null;
                equalPass = true;
            } else {
                if (!operations.getText().toString().isEmpty()) {
                    result = resultTemp;
                    operations.setText(numberOfDecimal.format(result));
                    String formatRemainder = "\n" + getString(R.string.Remainder) + " " + numberOfDecimal.format(remainder);
                    operations.append(formatRemainder);
                    stringNumberInsert = "";
                    buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
                    remainder = null;
                    previousValue = null;
                    value = null;
                    resultTemp = null;
                    operator = null;
                    equalPass = true;
                }
            }
            setOperation = result.toString();
            stringNumberInsert = setOperation;
        }
    }

    // ------------------------Functional---------------------------------------------------------------------------------------------------

    public void onClickToolbar(View view) {
    }

    public void onClickTextViewToolbar(View view) {
        if (!checkclick) {
            String formatString = (getString(R.string.Greeting) + " " + new String(Character.toChars(0x1F609)));
            textViewToolbar.setText(formatString);
            checkclick = true;
        } else {
            textViewToolbar.setText(getString(R.string.app_name));
            checkclick = false;
        }

    }

    public void onClickAuthor(View view) {
        Toast.makeText(this, getString(R.string.remaining_click) + " " + (5 - count) + " click", Toast.LENGTH_SHORT).show();

        if (count == 5) {
            Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
            startActivity(intent);
            count = 0;
        }

        count++;
    }

    public void onClickOperations(View view) {
    }

    public void onClickReset(View view) {
        operations.setText("");
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        previousValue = null;
        value = null;
        remainder = null;
        operator = null;
        resultTemp = null;
        result = null;
        equalPass = false;
    }

    public void onClickDelete(View view) {

        String textViewString = operations.getText().toString();

        if (textViewString.equals("")) {
            operations.setText("");
            setOperation = "";
            previousValue = null;
            value = null;
            remainder = null;
            operator = null;
            resultTemp = null;
            result = null;
            stringNumberInsert = "";
            buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        }

        if (!textViewString.isEmpty() && result == null) {

            operations.setText(textViewString.substring(0, textViewString.length() - 1));
            setOperation = textViewString.substring(0, textViewString.length() - 1);
            stringNumberInsert = setOperation;
        }

        if (!stringNumberInsert.equals("") && result == null) {

            operations.setText(textViewString.substring(0, textViewString.length() - 1));
            setOperation = textViewString.substring(0, textViewString.length() - 1);
            stringNumberInsert = setOperation;
        }

        if (!textViewString.equals("")) {
            final boolean equals = textViewString.substring(0, textViewString.length() - 1).equals("");
            if (result != null && !equals) {
                operations.setText(textViewString.substring(0, textViewString.length() - 1));
                setOperation = textViewString.substring(0, textViewString.length() - 1);
                result = Double.parseDouble(setOperation);
            }
            if (equals) {
                operations.setText("");
                setOperation = "";
                result = null;
            }

        }

    }

    public void onClickPosNeg(View view) {
        String formatTextView = operations.getText().toString();

        if (stringNumberInsert == null) {
        }

        String formatString = "";
        if (formatTextView.equals("") || formatTextView.equals("+")) {
            formatString = "-";
            operations.setText(formatString);
        } else if (formatTextView.equals("-")) {
            formatString = "+";
            operations.setText(formatString);
        } else {
//            if (formatString.equals("-" + stringNumberInsert)) {
//                formatString = "" + stringNumberInsert;
//                operations.setText(formatString);
//            } else {
//                formatString = "-" + stringNumberInsert;
//                operations.setText(formatString);
//            }
        }

        if (operator != null) {

        }

        if (result != null) {
            if (result > 0) {
                formatString = "-" + result;
                operations.setText(formatString);
                result = Double.parseDouble(formatString);

            } else if (result < 0) {
                formatString = result.toString().replace("-", "+");
                operations.setText(formatString);
                result = Double.parseDouble(formatString);
            }

        }


//        previousValue = Double.parseDouble(formatString + stringNumberInsert);
        //todo
    }

    public void onClickComma(View view) {
        //todo

        if (!stringNumberInsert.equals("")) {
            setOperation = stringNumberInsert + ",";
            commaValue = true;
            operations.setText(setOperation);

            previousValue = Double.parseDouble(stringNumberInsert + ".");
        }

        if (stringNumberInsert.equals("") && !commaValue) {
            operations.setText("");

            setOperation = "";
            commaValue = false;
            previousValue = null;
            stringNumberInsert = "";
        }

        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        stringNumberInsert = "";
    }

    //todo sostituire con i miei annunci prima di pubblicare e fare 2 versioni in 2 branch separati,
    // la versione per il Playstore non avrà i click sull'autore

}