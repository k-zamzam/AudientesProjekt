


/*
* Vi har benyttet os af følgende guide til at hjælpe os med at implementere denne funktion:
*
*
*
* https://www.youtube.com/watch?v=rRoHBWKQoRE&t=184s og https://www.youtube.com/watch?v=D-UsLR-cdwg&t=1s
*
*
* */



package com.example.audientesprojekt;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.audientesprojekt.Services.NotificationActionService;
import com.example.audientesprojekt.librarylogic.LibraryFile;

import java.util.ArrayList;

public class NotificationExample extends Application {
    Notification.MediaStyle style = new Notification.MediaStyle();
    private final String CHANNEL_ID = "channel1";
    public static final String ACTION_PREV= "actionprev";
    public static final String ACTION_PLAY= "actionplay";
    public static final String ACTION_NEXT= "actionnext";

    private Notification notification;
    NotificationManager manager;

    public void creatNotification(Context context, ArrayList<LibraryFile> songs, int playbutton, int pos) {


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cover_placeholder);


        int drw_prev;
        Intent intentPrev = new Intent(context, NotificationActionService.class).setAction(ACTION_PREV);
        PendingIntent pendingPrev = PendingIntent.getBroadcast(context, 0, intentPrev, PendingIntent.FLAG_UPDATE_CURRENT);
        drw_prev = R.drawable.ic_baseline_skip_previous_24;

        Intent intentPlay = new Intent(context, NotificationActionService.class).setAction(ACTION_PLAY);
        PendingIntent pendingPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);

        int drw_next;
        Intent intentNext = new Intent(context, NotificationActionService.class).setAction(ACTION_NEXT);
        PendingIntent pendingNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
        drw_next = R.drawable.ic_baseline_skip_next_24;


        notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setLargeIcon(icon)
                .setContentTitle("Audientes")
                .setContentText(songs.get(pos).getFileName())
                .addAction(drw_prev, "prev", pendingPrev)
                .addAction(playbutton, "play", pendingPlay)
                .addAction(drw_next, "next", pendingNext)
                .setOngoing(true)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2))
                .setOnlyAlertOnce(true)
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManagerCompat.notify(1, notification);
    }


    public String getChannelId() {
        return CHANNEL_ID;
    }
}
