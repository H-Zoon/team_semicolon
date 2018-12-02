package com.semicolon.project.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;


public class AlarmControl extends MainActivity {

    public AlarmControl() {
        // TODO Auto-generated constructor stub
    }

   public void AddAlarm(String name) {
        // 알람리시버 intent 생성
       Log.d("aaaa", "난되는데 ");

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("text", name);
        intent.putExtra("id", 11111);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);

        AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    /*public void DelAlarm() {
        AlarmManager alarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(notifyTest.this, AlarmReceiver.class);
        intent.putExtra("text", "1st text");
        intent.putExtra("id", 11111);
        PendingIntent pender = PendingIntent.getBroadcast(notifyTest.this, 1, intent, 1);
        alarm.cancel(pender);
    }*/

}
