package com.danyjokerface.calculator;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class CalculatorWidget extends AppWidgetProvider {
    @Override
    public void onEnabled(@NonNull Context context) {
        Intent intent = new Intent(context, HomeScreen.class);
        context.startActivity(intent);
    }

}