package com.example.projerockpaperscissors;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class GameModeWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.game_mode_widget);

            // Tek Oyuncu için Intent
            Intent singleIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingSingle = PendingIntent.getActivity(context, 0, singleIntent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.btnSingle, pendingSingle);

            // İki Oyuncu için Intent
            Intent twoIntent = new Intent(context, TwoPlayers.class);
            PendingIntent pendingTwo = PendingIntent.getActivity(context, 1, twoIntent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.btnTwo, pendingTwo);

            // Widget'ı güncelle
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
