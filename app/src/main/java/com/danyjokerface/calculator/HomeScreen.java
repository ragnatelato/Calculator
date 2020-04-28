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

@SuppressWarnings("FieldCanBeLocal")  //todo remove
public class HomeScreen extends AppCompatActivity {
    private static int count = 0;
    private static Double value = 0.0;  //todo update
    private static Double remainder = 0.0;
    private static Double previousValue; //todo update
    private TextView textView;
    private TextView author;
    private Animation fade_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        textView = findViewById(R.id.operations);
        author = findViewById(R.id.author);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        author.startAnimation(fade_in);
    }

    // ------------------------Insert Number--------------------------------------------------------

    public void insertNumberOnTextView(@NotNull Integer number_to_insert) {
        textView.append(number_to_insert.toString());
        value=number_to_insert.doubleValue();
        //todo update value
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

    // ------------------------Operators------------------------------------------------------------

    public void onClickRemainder(View view) {
        remainder = previousValue % value;
    }

    public void onClickDivision(View view) {
        value = previousValue / value;
    }

    public void onClickMultiplication(View view) {
        value = previousValue * value;
    }

    public void onClickSubtraction(View view) {
        value = previousValue - value;
    }

    public void onClickSum(View view) {
       //limit insert only one + before insert another number
        textView.append("+");
        //value = previousValue + value;
    }

    public void onClickEqual(View view) {
        textView.setText(value.toString()); //todo
    }

    // ------------------------Functional---------------------------------------------------------------

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
//        textView.toString().substring(0, textView.length() - 1);
    }

    public void onClickPosNeg(View view) {
//        if (textView.toString().equals("")) {
//            textView.append("-");
//            //value =
//        } else {
//            textView.toString().substring(textView.lenght());
//        }
    }

    public void onClickComma(View view) {
        // insert logic
    }

}