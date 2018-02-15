package net.quatur.bootservice;

/**
 * Created by philipp on 12.02.18.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MyJobIntentService extends JobIntentService {
    /// Unique job ID
    static final int JOB_ID = 1001;
    static final String TAG = "MyJobIntentService";

    /// Convenience method for enqueuing
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        /// We have received work to do.  The system or framework is already
        /// holding a wake lock for us at this point, so we can just go.
        int notificationId = 1;
        while(true) {
            setNotification(getApplicationContext(), notificationId);
            Log.d(TAG, "set notification");
            try {
                Thread.sleep(60000); /// do something every minute
            } catch (InterruptedException e) {
            }
        }
    }

    public void setNotification(Context context, int notificationId) {

        String contentText = "error";
        Random myRandom = new Random();
        NotificationManager mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        switch (myRandom.nextInt(6)) {
            case 0:
                contentText = "Text 0";
                break;
            case 1:
                contentText = "Text 1";
                break;
            case 2:
                contentText = "Text 2";
                break;
            case 3:
                contentText = "Text 3";
                break;
            case 4:
                contentText = "Text 4";
                break;
            case 5:
                contentText = "Text 5";
                break;
            default:
                contentText = "Default Text";
        }

        /// Intent to launch activity if the user selects the notification
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("mText", contentText);

        PendingIntent contentIntent = PendingIntent.getActivity(context, notificationId, notificationIntent, 0);

        /// create new notification
        Notification notification = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Title") /// first row
                .setContentText(contentText) /// second row
                .setContentIntent(contentIntent) /// the intent to launch when touched
                .setSmallIcon(R.drawable.ic_launcher_foreground) /// without it, System UI crashes on version O
                .setChannelId(MainActivity.CHANNEL_ID)
                .setAutoCancel(true)
                .build();

        mManager.notify(1, notification); // id 1, only one notification
    }



}