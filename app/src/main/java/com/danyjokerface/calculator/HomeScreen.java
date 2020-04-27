package com.danyjokerface.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
    private static int count = 0;
    private static Double value = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

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


    public void onClickReset(View view) {
        value = 0.0;
    }


    //TODO inserire nell'ordine dei pulsanti dall'alto verso il basso


    public void onClickPosNeg(View view) {
        TextView textView = findViewById(R.id.author);
        if (textView.toString().equals("")) {
            textView.append("-");
            //value.doubleValue("-");

        } else {
            textView.clearComposingText();
        }

    }

    public void onClickZero(View view) {
        TextView textView = findViewById(R.id.author);
        textView.append("0");
    }

}