package com.ambush.gcmtutorial.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.util.Log

open class BaseActivity : AppCompatActivity() {

	var broadcastReceiver: ActivityBroadcastReceiver? = null

	override fun onStart() {
		super.onStart()
		broadcastReceiver = ActivityBroadcastReceiver()
		val filter = IntentFilter("com.ambush.gcmtutorial.gcm.PushReceived")
		filter.priority = 100
		registerReceiver(broadcastReceiver, filter)
	}

	override fun onStop() {
		super.onStop()
		unregisterReceiver(broadcastReceiver)
		broadcastReceiver = null
	}

	fun handleNotification(): Boolean {
		return false
	}

	inner class ActivityBroadcastReceiver : BroadcastReceiver() {
		override fun onReceive(cancel: Context, intent: Intent) {
			handleNotification()
			Log.i("ActivityBroadcastReceiver", "Received message")
			abortBroadcast()
		}

	}
}
