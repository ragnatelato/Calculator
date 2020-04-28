package com.danyjokerface.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

public class HomeScreen extends AppCompatActivity {
    private static int count = 0;
    private static Integer value = 0;
    private TextView textView = findViewById(R.id.operations);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    //TODO customizzare toolbar

    public void onClickAuthor(View view) {
        //TODO il toast è troppo lento a comparire ed aggiornarsi
//        count++;
//        Toast.makeText(getApplicationContext(), "Ti mancano ancora " + (5 - count) + " click", Toast.LENGTH_SHORT).show();
//        if (count == 5) {
//            Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
//            intent.putExtra("Benvenuto", "Ho cambiato l'activity");
//            startActivity(intent);
//            count = 0;
//        }

    }

    public void onClickOperations(View view) {
        // inserire l'eventuale logica
    }


    public void onClickReset(View view) {
        textView.append("");
        value = 0;
    }

    public void onClickDelete(View view) {
        // inserire l'eventuale logica
    }

    public void onClickRemainder(View view) {
        // inserire l'eventuale logica
    }

    public void onClickDivision(View view) {
        // inserire l'eventuale logica
    }

    public void insertNumberOnTextView(@NotNull Integer number_to_insert) {
        value = number_to_insert;
        textView.append(number_to_insert.toString());
    }

    //TODO inserire nell'ordine dei pulsanti dall'alto verso il basso


    public void onClickPosNeg(View view) {
//        if (textView.toString().equals("")) {
//            textView.append("-");
//            //value.doubleValue("-");
//
//        } else {
//            textView.clearComposingText();
//        }

    }

    public void onClickZero(View view) {
        insertNumberOnTextView(0);
    }

}