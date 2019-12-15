package com.example.bataryinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text_info);
        btn = findViewById(R.id.btninfo);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            }
        });
    }

    BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            boolean present = Objects.requireNonNull(intent.getExtras()).getBoolean(BatteryManager.EXTRA_PRESENT);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int tempratura = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

            tv.setText("health = " + health + "\n" +
                    "zaryad miqdori = " + level+"%" + "\n" +
                    "plugged = " + plugged + "\n" +
                    "present = " + present + "\n" +
                    "status = " + status + "\n" +
                    "texnalogiya = " + technology + "\n" +
                    "tempratura = " + tempratura + "\n" +
                    "kuchlanish = " + voltage);
        }
    };
}
