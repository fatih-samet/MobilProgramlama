package com.example.projerockpaperscissors;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.util.Random;

public class TwoPlayerWidget extends AppWidgetProvider {

    enum Choises { ROCK, PAPER, SCISSORS }

    private static final String ACTION_PLAYER1 = "PLAYER1_CLICK";
    private static final String ACTION_PLAYER2 = "PLAYER2_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.two_player_widget);

            // Set up PendingIntents
            Intent intent1 = new Intent(context, TwoPlayerWidget.class);
            intent1.setAction(ACTION_PLAYER1);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.btnWidgetPlayer1, pendingIntent1);

            Intent intent2 = new Intent(context, TwoPlayerWidget.class);
            intent2.setAction(ACTION_PLAYER2);
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.btnWidgetPlayer2, pendingIntent2);

            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        SharedPreferences prefs = context.getSharedPreferences("widgetPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Choises player1Choice = null;
        Choises player2Choice = null;

        int player1Score = prefs.getInt("score1", 0);
        int player2Score = prefs.getInt("score2", 0);
        boolean isPlayer1Turn = prefs.getBoolean("isP1Turn", true);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.two_player_widget);

        if (intent.getAction().equals(ACTION_PLAYER1) && isPlayer1Turn) {
            Choises choice = getRandomChoice();
            player1Choice = choice;
            editor.putString("choice1", choice.name());
            editor.putBoolean("isP1Turn", false);

            views.setImageViewResource(R.id.imageWidgetPlayer1, getDrawableId(choice));
        } else if (intent.getAction().equals(ACTION_PLAYER2) && !isPlayer1Turn) {
            player1Choice = Choises.valueOf(prefs.getString("choice1", ""));
            player2Choice = getRandomChoice();
            views.setImageViewResource(R.id.imageWidgetPlayer2, getDrawableId(player2Choice));
            editor.putBoolean("isP1Turn", true);

            if (player1Choice != null && player2Choice != null) {
                if (player1Choice != player2Choice) {
                    if ((player1Choice == Choises.ROCK && player2Choice == Choises.SCISSORS) ||
                            (player1Choice == Choises.PAPER && player2Choice == Choises.ROCK) ||
                            (player1Choice == Choises.SCISSORS && player2Choice == Choises.PAPER)) {
                        player1Score++;
                    } else {
                        player2Score++;
                    }
                }
            }

            if (player1Score == 3 || player2Score == 3) {
                player1Score = 0;
                player2Score = 0;
            }

            editor.putInt("score1", player1Score);
            editor.putInt("score2", player2Score);
        }

        views.setTextViewText(R.id.textWidgetScore, "P1: " + player1Score + " P2: " + player2Score);

        editor.apply();

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName widget = new ComponentName(context, TwoPlayerWidget.class);
        manager.updateAppWidget(widget, views);
    }

    private Choises getRandomChoice() {
        int r = new Random().nextInt(3);
        if (r == 0) return Choises.ROCK;
        if (r == 1) return Choises.PAPER;
        return Choises.SCISSORS;
    }

    private int getDrawableId(Choises choice) {
        switch (choice) {
            case ROCK:
                return R.drawable.rock;
            case PAPER:
                return R.drawable.paper;
            case SCISSORS:
                return R.drawable.scissors;
        }
        return R.drawable.temp;
    }
}
