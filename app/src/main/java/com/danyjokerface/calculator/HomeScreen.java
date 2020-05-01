package com.danyjokerface.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

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
    DecimalFormatSymbols decimalFormatSymbols;
    DecimalFormat numberOfDecimal;
    private String stringNumberInsert = "";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        textView = findViewById(R.id.operations);
        TextView author = findViewById(R.id.author);
        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        author.startAnimation(fade_in);
        buildStringToValue = new StringBuilder();
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        numberOfDecimal = new DecimalFormat("#.##########", decimalFormatSymbols);
    }

    // ------------------------Insert Number--------------------------------------------------------------------------------------

    public void insertNumberOnTextView(@NotNull Integer number_to_insert) {
        stringNumberInsert = buildStringToValue.append(number_to_insert).toString();

        if (result != null && equalPass) {
            textView.setText("");
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

        textView.append(number_to_insert.toString());
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

    // ------------------------Operators------------------------------------------------------------------------------------------

    public void changeOperatorOnTheFly(String operator) {
        String TempResultView = numberOfDecimal.format(previousValue) + operator;
        textView.setText(TempResultView);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void insertFirstOperator(String operator) {
        previousValue = Double.parseDouble(stringNumberInsert);
        textView.append(operator);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void insertMoreOperator(String operator) {
        HomeScreen.operator = null;
        previousValue = resultTemp;
        String TempResultView = numberOfDecimal.format(resultTemp) + operator;
        textView.setText(TempResultView);
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
    }

    public void operatorAfterEqual(String operator) {
        String TempResultView = numberOfDecimal.format(result) + operator;
        textView.setText(TempResultView);
        previousValue = result;
        stringNumberInsert = "";
        buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
        HomeScreen.operator = operator;
        equalPass = false;
    }
    

    public void onClickPercentage(View view) {
        if (value == null && operator != null) {
            changeOperatorOnTheFly("%");
        }

        if (operator == null && previousValue == null && !equalPass) {
            insertFirstOperator("%");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("%");
        }

        if (result != null) {
            operatorAfterEqual("%");
        }

    }

    public void onClickDivision(View view) {
        if (value == null && operator != null) {
            changeOperatorOnTheFly("/");
        }

        if (operator == null && previousValue == null && !equalPass) {
            insertFirstOperator("/");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("/");
        }

        if (result != null) {
            operatorAfterEqual("/");
        }

    }

    public void onClickMultiplication(View view) {
        if (value == null && operator != null) {
            changeOperatorOnTheFly("*");
        }

        if (operator == null && previousValue == null && !equalPass) {
            insertFirstOperator("*");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("*");
        }

        if (result != null) {
            operatorAfterEqual("*");
        }

    }

    public void onClickSubtraction(View view) {
        if (value == null && operator != null) {
            changeOperatorOnTheFly("-");
        }

        if (operator == null && previousValue == null && !equalPass) {
            insertFirstOperator("-");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("-");
        }

        if (result != null) {
            operatorAfterEqual("-");
        }

    }

    public void onClickSum(View view) {
        if (value == null && operator != null) {
            changeOperatorOnTheFly("+");
        }

        if (operator == null && previousValue == null && !equalPass) {
            insertFirstOperator("+");
        } else if (previousValue != null && !equalPass && value != null) {
            insertMoreOperator("+");
        }

        if (result != null) {
            operatorAfterEqual("+");
        }

    }

    public void onClickEqual(View view) {
        if (resultTemp != null && !equalPass && remainder == null) {
            result = resultTemp;
            textView.setText(numberOfDecimal.format(result));
            stringNumberInsert = "";
            buildStringToValue = buildStringToValue.delete(0, buildStringToValue.length());
            previousValue = null;
            value = null;
            resultTemp = null;
            operator = null;
            equalPass = true;
        } else {
            result = resultTemp;
            textView.setText(numberOfDecimal.format(result));
            String formatRemainder = "\n" + getString(R.string.Remainder) + " " + numberOfDecimal.format(remainder);
            textView.append(formatRemainder);
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

    // ------------------------Functional-----------------------------------------------------------------------------------------

    //TODO customize toolbar

    @SuppressLint("MissingPermission")
    public void onClickAuthor(View view) {
        //TODO toast too slow / change color toast day night theme
        count++;
        Toast.makeText(getApplicationContext(), "Ti mancano ancora " + (5 - count) + " click", Toast.LENGTH_SHORT).show();
        if (count == 5) {
            Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
            startActivity(intent);
            count = 0;
        }

    }

    public void onClickOperations(View view) {
    }

    public void onClickReset(View view) {
        textView.setText("");
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
        if (result != null) {
            //todo fix quando lo faccio su un risultato o su un operatore
            String textViewString = textView.getText().toString();
            textView.setText(textViewString.substring(0, textViewString.length() - 1));
            result = Double.parseDouble(result.toString().substring(0, result.toString().length() - 1));
            textView.setText(numberOfDecimal.format(result));
            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
            buildStringToValue = buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
        }
        if (!stringNumberInsert.equals("")) {
            String textViewString = textView.getText().toString();
            textView.setText(textViewString.substring(0, textViewString.length() - 1));
            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
            buildStringToValue = buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
        }

    }

    public void onClickPosNeg(View view) {
        int searchOfSign = stringNumberInsert.indexOf("-");

        if (searchOfSign != 0) {
            String textViewString = textView.getText().toString();
            textView.setText(textViewString.substring(0,
                    Integer.parseInt(textViewString.concat("111" + textView))));
//            stringNumberInsert = stringNumberInsert.substring(0, stringNumberInsert.length() - 1);
//            buildStringToValue = buildStringToValue.deleteCharAt(buildStringToValue.length() - 1);
        } else {
        }

    }


    public void onClickComma(View view) {
//        textView.append(",");
//        String textViewString = textView.getText().toString().replace(",",".");
//        value = Double.parseDouble(textViewString);
        // insert logic
        //todo
    }

}