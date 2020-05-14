package com.danyjokerface.calculator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * @author DanyJokerFace
 * @version 1.0
 */

public class HomeScreen extends AppCompatActivity {
    //Object Class
    Input input;
    Functional functional;
    //Views
    TextView operations;
    TextView greetingsToolbarView;
    Animation fade_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // initialize object
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        input = new Input();
        functional = new Functional();
        operations = findViewById(R.id.operations);
        greetingsToolbarView = findViewById(R.id.textViewToolbar);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        greetingsToolbarView.startAnimation(fade_in);

        //ads
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    // ------------------------Insert Number------------------------------------------------------------------------------------------------

    public void onClickNine(View view) {
        operations.setText(input.appendNumberForTextView(9));
    }

    public void onClickEight(View view) {
        operations.setText(input.appendNumberForTextView(8));
    }

    public void onClickSeven(View view) {
        operations.setText(input.appendNumberForTextView(7));
    }

    public void onClickSix(View view) {
        operations.setText(input.appendNumberForTextView(6));
    }

    public void onClickFive(View view) {
        operations.setText(input.appendNumberForTextView(5));
    }

    public void onClickFour(View view) {
        operations.setText(input.appendNumberForTextView(4));
    }

    public void onClickThree(View view) {
        operations.setText(input.appendNumberForTextView(3));
    }

    public void onClickTwo(View view) {
        operations.setText(input.appendNumberForTextView(2));
    }

    public void onClickOne(View view) {
        operations.setText(input.appendNumberForTextView(1));
    }

    public void onClickZero(View view) {
        operations.setText(input.appendNumberForTextView(0));
    }

    // ------------------------Operators----------------------------------------------------------------------------------------------------

    public void onClickPercentage(View view) {
        operations.setText(input.insertOperator('%'));
    }

    public void onClickDivision(View view) {
        operations.setText(input.insertOperator('÷'));
    }

    public void onClickMultiplication(View view) {
        operations.setText(input.insertOperator('*'));
    }

    public void onClickSubtraction(View view) {
        operations.setText(input.insertOperator('-'));
    }

    public void onClickSum(View view) {
        operations.setText(input.insertOperator('+'));
    }

    // ------------------------Equal--------------------------------------------------------------------------------------------------------

    public void onClickEqual(View view) {
        String remainderInsert = getString(R.string.Remainder);
        operations.setText(input.equal(remainderInsert));
    }

    // ------------------------Functional---------------------------------------------------------------------------------------------------

    public void onClickToolbar(View view) {
        functional.setToolbar();
    }

    //greetings joke
    public void onClickTextViewToolbar(View view) {
        String stringGreeting = getString(R.string.Greeting);
        String appName = getString(R.string.app_name);
        greetingsToolbarView.setText(functional.setTextViewToolbar(stringGreeting, appName));
    }

    public void onClickOperations(View view) {
        functional.setOperations();
    }

    public void onClickReset(View view) {
        operations.setText("");
        input.resetAll();
    }

    public void onClickDelete(View view) {
        operations.setText(input.deleteSet());
    }

    public void onClickPosNeg(View view) {
        operations.setText(input.posNegSet());
    }

    public void onClickComma(View view) {
        operations.setText(input.commaSet());
    }

    ///////////////////////////////////////////
    // 1) a fine programmazione implementare i "thread UI", x vedere se il programma si velocizza e non da piu il messaggio "skipped frame"
    // 2) sostituire con i miei annunci prima di pubblicare
    // 3) fare 2 versioni in 2 branch separati, la versione per il Playstore non avrà i click sull'autore
    ///////////////////////////////////////////

}