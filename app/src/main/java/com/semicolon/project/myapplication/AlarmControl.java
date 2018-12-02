package com.semicolon.project.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import java.util.Calendar;


public class AlarmControl extends MainActivity {

    public AlarmControl() {
        // TODO Auto-generated constructor stub
    }

   public void AddAlarm(Cursor c) {
        // 알람리시버 intent 생성

       int id = 0;
       String name = "";

       while(c.moveToNext()){
           id = c.getInt(0);
           name = c.getString(1);
       }

       Intent intent = new Intent(context, AlarmReceiver.class);
       intent.putExtra("ID", id);
       intent.putExtra("Name", name);
       PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

       Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(System.currentTimeMillis());
       calendar.add(Calendar.SECOND, 5);

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
