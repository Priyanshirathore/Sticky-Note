package com.example.note_me_down;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetid : appWidgetIds)
        {
            Intent launchintent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(context,0,launchintent,0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            remoteViews.setOnClickPendingIntent(R.id.idTextWidget,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetid, remoteViews);
        }
    }
}
