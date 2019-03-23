package com.skynet.choviet.utils;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by framgia on 23/05/2017.
 */
public class AlarmUtils {
    private static int INDEX = 1;

    public static void create(Context context, String hour,String tite,String content) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.split(":")[0])); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE,  Integer.parseInt(hour.split(":")[1]));
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SchedulingService.class);
        intent.putExtra("type", INDEX);
        intent.putExtra("title", tite);
        intent.putExtra("content", content);
        PendingIntent pendingIntent =
                PendingIntent.getService(context, INDEX, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        INDEX++;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
//        alarmManager
//                .setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(3, CommomUtils.createNotificationWithMsgStick(context, "vinenglish Trip", "Hệ thống đã ghi nhận chuyến đi đặt trước của bạn!"));

    }
}
