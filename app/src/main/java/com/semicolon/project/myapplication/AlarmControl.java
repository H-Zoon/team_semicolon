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
        String Date = "";

        while(c.moveToNext()){
            id = c.getInt(0);
            name = c.getString(1);
            Date = c.getString(3);
        }

        Log.d("name and id","name: " + name + "ID: "+id);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("ID", id);
        intent.putExtra("Name", name);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());

//시간 저장

        String[] Data_arr = Date.split("/");

        calendar.set(Calendar.YEAR, Integer.parseInt(Data_arr[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(Data_arr[1])-1);
        calendar.set(Calendar.DATE, Integer.parseInt(Data_arr[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        //calendar.add(Calendar.SECOND, 5); // 테스트용

        AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

    }

    public void DelAlarm(Cursor c) {
        int id = 0;
        String name = "";

        while(c.moveToNext()){
            id = c.getInt(0);
            name = c.getString(1);
        }

        AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("ID", id);
        intent.putExtra("Name", name);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarm.cancel(sender);
    }

    public void DelAlarm_All(Cursor c) {
        int id = 0;
        String name = "";

        while(c.moveToNext()){
            id = c.getInt(0);
            name = c.getString(1);

            AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra("ID", id);
            intent.putExtra("Name", name);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarm.cancel(sender);
        }
    }

}
