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
    private static Double previousValue;
    private static Double value = null;
    private static Double remainder = 0.0;
    private static String operator = null;
    private static Double resultTemp = null;
    private static Double result = null;
    DecimalFormatSymbols decimalFormatSymbols;
    DecimalFormat numberOfDecimal;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        textView = findViewById(R.id.operations);
        TextView author = findViewById(R.id.author);
        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        author.startAnimation(fade_in);
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        numberOfDecimal = new DecimalFormat("#.##########", decimalFormatSymbols);
    }

    // ------------------------Insert Number------------------------------------------------------------------

    public void insertNumberOnTextView(@NotNull Integer number_to_insert) {
        if (result != null) {
            textView.setText("");
        }

        if (operator != null) {
            //todo update
            previousValue = value;
            value = number_to_insert.doubleValue();
            switch (operator) {
                case ("/"):
                    resultTemp = previousValue / value;
                    remainder = previousValue % value;
                    operator = null;
                    break;
                case ("*"):
                    resultTemp = previousValue * value;
                    operator = null;
                    break;
                case ("-"):
                    resultTemp = previousValue - value;
                    operator = null;
                    break;
                case ("+"):
                    resultTemp = previousValue + value;
                    operator = null;
                    break;
                //todo implement remainder and more number consecutive
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

    // ------------------------Operators----------------------------------------------------------------------

    public void onClickRemainder(View view) {
        if (operator == null) {
            String formatRemainder = getString(R.string.Remainder) + " " + numberOfDecimal.format(remainder);
            textView.setText(formatRemainder);
            remainder = null;
        }
    }

    public void onClickDivision(View view) {
        if (operator == null) {
            String textViewString = textView.getText().toString();
            textView.append("/");
            operator = "/";
            value = Double.parseDouble(textViewString);
        }
    }

    public void onClickMultiplication(View view) {
        if (operator == null) {
            String textViewString = textView.getText().toString();
            textView.append("*");
            operator = "*";
            value = Double.parseDouble(textViewString);
        }
    }

    public void onClickSubtraction(View view) {
        if (operator == null) {
            String textViewString = textView.getText().toString();
            textView.append("-");
            operator = "-";
            value = Double.parseDouble(textViewString);
        }
    }

    public void onClickSum(View view) {
        String textViewString = textView.getText().toString();
        if (operator == null && resultTemp == null) {
            textView.append("+");
            operator = "+";
            value = Double.parseDouble(textViewString);
        } else {
            String textViewCleanOperator = textViewString.substring(textViewString.indexOf("+") + 1);
            value = Double.parseDouble(textViewCleanOperator);
            String TempResultView = numberOfDecimal.format(resultTemp) + " + " + numberOfDecimal.format(value);
            textView.setText(numberOfDecimal.format(TempResultView));
            operator = "+";
        }

    }

    public void onClickEqual(View view) {
        result = resultTemp;
        textView.setText(numberOfDecimal.format(result));
        previousValue = null;
        value=null;
        resultTemp=null;
        result = null;
    }

    // ------------------------Functional---------------------------------------------------------------------

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
        // insert logic
    }

    public void onClickReset(View view) {
        textView.setText("");
        //todo update value
    }

    public void onClickDelete(View view) {
//        textView.setText(textView.length());
//        textView.toString().substring(0, textView.length() - 1); //todo
    }

    public void onClickPosNeg(View view) {
//        if (textView.toString().equals("")) {
//            textView.append("-");
//            //value =
//        } else {
//            textView.toString().substring(textView.lenght()); //todo
//        }
    }

    public void onClickComma(View view) {
        // insert logic
    }

}