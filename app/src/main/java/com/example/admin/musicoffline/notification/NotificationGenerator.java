package com.example.admin.musicoffline.notification;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.admin.musicoffline.MainActivity;
import com.example.admin.musicoffline.R;

public class NotificationGenerator {

    public static final String NOTIFY_PREVIOUS = "com.example.admin.musicoffline.notification.previous";
    public static final String NOTIFY_DELETE = "com.example.admin.musicoffline.notification.delete";
    public static final String NOTIFY_PAUSE = "com.example.admin.musicoffline.notification.pause";
    public static final String NOTIFY_PLAY = "com.example.admin.musicoffline.notification.play";
    public static final String NOTIFY_NEXT = "com.example.admin.musicoffline.notification.next";

    private static final int NOTIFICATION_ID_OPEN_ACTIVITY = 9;
    public static void openActivityNotification(Context applicationContext) {
        NotificationCompat.Builder nc = new NotificationCompat.Builder(applicationContext);
        NotificationManager nm = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(applicationContext, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 0,
            notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.mipmap.ic_launcher);
        nc.setAutoCancel(true);
        nc.setContentTitle("Notification Demo");
        nc.setContentText("Click please");

        nm.notify(NOTIFICATION_ID_OPEN_ACTIVITY, nc.build());

    }

    public static void customBigNotification(Context applicationContext) {

        RemoteViews expandedView = new RemoteViews(applicationContext.getPackageName(),
                R.layout.bg_notification);
       NotificationCompat.Builder nc = new NotificationCompat.Builder(applicationContext);
       NotificationManager ns = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
       Intent notifyIntent = new Intent(applicationContext,NotificationActivity.class);

        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext,0 , notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.drawable.iconpause);

        nc.setAutoCancel(true);
        nc.setCustomBigContentView(expandedView);
        nc.setContentTitle("Music Player");
        nc.setContentText("Control Audio");

        nc.getBigContentView().setTextViewText(R.id.tvNameSong,"Adele");

        setLiseners(expandedView,applicationContext);

    }

    private static void setLiseners(RemoteViews views, Context context){
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent play = new Intent(NOTIFY_PLAY);
        Intent next = new Intent(NOTIFY_NEXT);

        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imgPrev, pPrevious);

        PendingIntent pDelete = PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnDelete, pPrevious);

        PendingIntent pPause = PendingIntent.getBroadcast(context, 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imgPause, pPrevious);

        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imgPlay, pPrevious);

        PendingIntent pNext = PendingIntent.getBroadcast(context, 0,next, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imgNext, pPrevious);



    }
}
