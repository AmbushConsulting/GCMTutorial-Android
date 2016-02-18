package com.ambush.gcmtutorial.gcm

import PreferencesHelper
import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.android.gms.gcm.GoogleCloudMessaging
import com.google.android.gms.iid.InstanceID

class GCMRegistrationIntentService : IntentService("GCMRegistrationIntentService") {

    val TAG = "GCMRegistrationIntentService"

    override fun onHandleIntent(intent: Intent) {
        val instanceID = InstanceID.getInstance(this)
        val token = instanceID.getToken("745439967320", GoogleCloudMessaging.INSTANCE_ID_SCOPE)

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        Log.i(TAG, "GCM Token - $token")
        PreferencesHelper.saveGCMToken(this, token)
        PreferencesHelper.setTokenSentToServer(this)
    }
}