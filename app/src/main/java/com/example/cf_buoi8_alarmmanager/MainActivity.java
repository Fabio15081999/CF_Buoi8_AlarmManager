package com.example.cf_buoi8_alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv1);
        SetAlarm();
    }

    public void SetAlarm()
    {
       // final TextView textView = tv[2]; // replace with a button from your own UI
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent _ )
            {
                tv.setTextColor( Color.RED );
                context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
            }
        };

        this.registerReceiver( receiver, new IntentFilter("com.blah.blah.somemessage") );

        PendingIntent pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );
        AlarmManager manager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));

        // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
        manager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000*15, pintent );
    }

}