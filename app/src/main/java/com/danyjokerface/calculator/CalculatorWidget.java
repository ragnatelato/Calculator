package com.danyjokerface.calculator;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class CalculatorWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context) {
        Intent intent = new Intent(context, HomeScreen.class);
        context.startActivity(intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent intent = new Intent(context, HomeScreen.class);
        context.startActivity(intent);

    }

    @Override
    public void onEnabled(Context context) {
        Intent intent = new Intent(context, HomeScreen.class);
        context.startActivity(intent);

    }

}

