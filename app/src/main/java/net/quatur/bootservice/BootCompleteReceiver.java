package net.quatur.bootservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by philipp on 12.02.18.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.wtf(TAG, "Boot completed! Starting service...");
            MyJobIntentService.enqueueWork(context, intent);
        }
    }
}
