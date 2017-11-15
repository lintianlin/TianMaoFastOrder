package com.sinfeeloo.tianmao;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnDateSetListener {

    private TimePicker timePicker;
    private TextView time;
    private Calendar calendar;
    private int mHour;
    private int mMinute;
    private long selectTime;
    private TimePickerDialog dialogAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.tv_time);
        calendar = Calendar.getInstance();
    }


    public void setTime(View view) {
        dialogAll = new TimePickerDialog.Builder()
                .setType(Type.ALL)
                .setCallBack(this)
                .build();
        dialogAll.show(getSupportFragmentManager(), "All");
    }

    public void start(View view) {
        Toast.makeText(this, "开始监听", Toast.LENGTH_SHORT).show();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //set(f, value) changes field f to value.
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Intent intent = new Intent(MainActivity.this, AlermReceiver.class);
        intent.putExtra("music", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager am;

        //获取系统进程
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, selectTime, pendingIntent);

    }


    public void close(View view) {
        Toast.makeText(this, "关闭服务", Toast.LENGTH_SHORT).show();
        Intent stopIntent = new Intent(this, QuickOrderService.class);
        stopService(stopIntent);
        Intent intent = new Intent(MainActivity.this, AlermReceiver.class);
        intent.putExtra("music", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager am;
        //获取系统进程
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        //cancel
        am.cancel(pendingIntent);

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        selectTime = millseconds;
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//精确到分钟
        String timecontnet = format.format(date);
        time.setText("您选择的时间为：" + timecontnet);
    }
}
