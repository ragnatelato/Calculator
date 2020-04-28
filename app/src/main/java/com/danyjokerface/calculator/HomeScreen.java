package com.danyjokerface.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("FieldCanBeLocal")
public class HomeScreen extends AppCompatActivity {
    private static int count = 0;
    private static Double value = 0.0;
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
        //value = Double.parseDouble(textView);
        Log.d("result", "The value is: " + value);
    }

    public void onClickPosNeg(View view) {
//        if (textView.toString().equals("")) {
//            textView.append("-");
//            //value.doubleValue("-");
//
//        } else {
//            textView.clearComposingText();
//        }

    }

    public void onClickReset(View view) {
//        textView.append("");
//        value = 0.0;
//        Log.d("result", "The value is: " + value);
    }

    public void onClickDelete(View view) {
        // inserire l'eventuale logica
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
        // inserire l'eventuale logica
    }

    public void onClickDivision(View view) {
        // inserire l'eventuale logica
    }

    // ------------------------Functional---------------------------------------------------------------

    //TODO customizzare toolbar

    @SuppressLint("MissingPermission")
    public void onClickAuthor(View view) {

        //TODO il toast è troppo lento a comparire ed aggiornarsi
        count++;
        Toast.makeText(getApplicationContext(), "Ti mancano ancora " + (5 - count) + " click", Toast.LENGTH_SHORT).show();
        if (count == 5) {
            Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
            startActivity(intent);
            count = 0;
        }

    }

    public void onClickOperations(View view) {
        // inserire l'eventuale logica
    }

}