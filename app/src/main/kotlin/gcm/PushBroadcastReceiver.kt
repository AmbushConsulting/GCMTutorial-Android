package com.ambush.gcmtutorial.gcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class PushBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("PushBroadcastReceiver", "Received")
    }
}
