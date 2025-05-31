package com.example.projerockpaperscissors;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;
import java.util.Random;

public class SinglePlayerWidget extends AppWidgetProvider {

    public static final String ACTION_ROCK = "com.example.ROCK";
    public static final String ACTION_PAPER = "com.example.PAPER";
    public static final String ACTION_SCISSORS = "com.example.SCISSORS";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.single_player_widget);

            // Taş
            Intent rockIntent = new Intent(context, SinglePlayerWidget.class);
            rockIntent.setAction(ACTION_ROCK);
            PendingIntent rockPending = PendingIntent.getBroadcast(context, 0, rockIntent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.imgRock, rockPending);

            // Kağıt
            Intent paperIntent = new Intent(context, SinglePlayerWidget.class);
            paperIntent.setAction(ACTION_PAPER);
            PendingIntent paperPending = PendingIntent.getBroadcast(context, 1, paperIntent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.imgPaper, paperPending);

            // Makas
            Intent scissorsIntent = new Intent(context, SinglePlayerWidget.class);
            scissorsIntent.setAction(ACTION_SCISSORS);
            PendingIntent scissorsPending = PendingIntent.getBroadcast(context, 2, scissorsIntent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.imgScissors, scissorsPending);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        String result = "";

        if (action != null) {
            String playerMove = "";
            if (action.equals(ACTION_ROCK)) {
                playerMove = "Taş";
            } else if (action.equals(ACTION_PAPER)) {
                playerMove = "Kağıt";
            } else if (action.equals(ACTION_SCISSORS)) {
                playerMove = "Makas";
            }

            String computerMove = getRandomMove();
            result = getGameResult(playerMove, computerMove);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.single_player_widget);
            views.setTextViewText(R.id.textResult, "Sen: " + playerMove + "\nBot: " + computerMove + "\n" + result);
            views.setTextColor(R.id.textResult, result.equals("Kazandın!") ? Color.GREEN :
                    result.equals("Kaybettin!") ? Color.RED : Color.YELLOW);

            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            ComponentName widget = new ComponentName(context, SinglePlayerWidget.class);
            manager.updateAppWidget(widget, views);
        }
    }

    private String getRandomMove() {
        String[] moves = {"Taş", "Kağıt", "Makas"};
        Random random = new Random();
        return moves[random.nextInt(3)];
    }

    private String getGameResult(String player, String computer) {
        if (player.equals(computer)) return "Berabere";
        if ((player.equals("Taş") && computer.equals("Makas")) ||
                (player.equals("Kağıt") && computer.equals("Taş")) ||
                (player.equals("Makas") && computer.equals("Kağıt"))) {
            return "Kazandın!";
        }
        return "Kaybettin!";
    }
}
