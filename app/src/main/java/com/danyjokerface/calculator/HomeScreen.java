package com.danyjokerface.calculator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

//    TextView operations = findViewById(R.id.operations);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.d(HomeScreen.class.getName(), "sono nell'onCreate!");

//        button = findViewById(R.id.button_comma);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("MainActivity", "Ciao Dany!");
//            }
//        });
    }

    @Override
    protected void onStart() {
        Log.d(HomeScreen.class.getName(), "sono nell'onStart!");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(HomeScreen.class.getName(), "sono nell'onPause!");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(HomeScreen.class.getName(), "sono nell'onStop!");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(HomeScreen.class.getName(), "sono nell'onRestart!");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(HomeScreen.class.getName(), "sono nell'onDestroy!");
        super.onDestroy();
    }

//    public void onClickSum(View view) {
//        operations.append("+");
//        Log.d("MainActivity", "The result is: " + (10 + 33));
//    }

}