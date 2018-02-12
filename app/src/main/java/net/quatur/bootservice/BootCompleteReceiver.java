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
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ||
                intent.getAction().equals(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE) ) {
            /// ACTION_EXTERNAL_APPLICATIONS_AVAILABLE is guess work, picked up from here:
            /// http://blog.vogella.com/2011/12/11/automatically-starting-services-in-android-after-booting/
            Log.d(TAG, "action: " + intent.getAction());
            Log.d(TAG, "Boot completed! Starting service...");
            MyJobIntentService.enqueueWork(context, intent);
        }
    }
}
