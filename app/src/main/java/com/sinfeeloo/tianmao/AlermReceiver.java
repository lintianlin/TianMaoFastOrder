package com.sinfeeloo.tianmao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlermReceiver extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("mhj", "onReceive:======闹钟响了");
        Toast.makeText(context, "开始抢", Toast.LENGTH_SHORT).show();
        Intent startIntent = new Intent(context, QuickOrderService.class);
        context.startService(startIntent);
    }


}