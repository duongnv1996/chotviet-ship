package com.skynet.chovietship.utils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

public class SchedulingService extends IntentService {
    private static final int TIME_VIBRATE = 1000;

    public SchedulingService() {
        super(SchedulingService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int index = intent.getIntExtra("type", 0);
        String title = intent.getStringExtra("title");
        String contnt = intent.getStringExtra("content");
        Notification notification = CommomUtils.createNotificationWithMsg(this, title, contnt);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(3, notification);
    }
}
