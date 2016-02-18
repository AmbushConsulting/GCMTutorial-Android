package com.ambush.gcmtutorial.gcm

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.util.Log
import com.google.android.gms.gcm.GcmListenerService

class PushReceiverService : GcmListenerService() {

    val TAG = "PushReceiverService"

    override fun onMessageReceived(from: String?, data: Bundle?) {
        val message = data!!.getString("message")
        Log.d(TAG, "From: " + from!!)
        Log.d(TAG, "Message: " + message)


//        val mBuilder = NotificationCompat.Builder(this)
//                .setSmallIcon(android.R.drawable.sym_def_app_icon)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!");
//
//        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        // mId allows you to update the notification later on.
//        mNotificationManager.notify(666, mBuilder.build());
    }

}
