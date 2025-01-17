/*
* Vi benyttet følgende guide:
*
* https://www.youtube.com/watch?v=rRoHBWKQoRE&t=184s
* */

package com.example.audientesprojekt.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationActionService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.sendBroadcast(new Intent("PLAY_SONG").putExtra("actionname", intent.getAction()));
    }
}
