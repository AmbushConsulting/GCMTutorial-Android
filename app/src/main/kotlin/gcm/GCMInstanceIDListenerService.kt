package com.ambush.gcmtutorial.gcm

import android.content.Intent
import com.google.android.gms.iid.InstanceIDListenerService

class GCMInstanceIDListenerService : InstanceIDListenerService() {

    //     Called if InstanceID token is updated. This may occur if the security of
    //     the previous token had been compromised. This call is initiated by the
    //     InstanceID provider.
    override fun onTokenRefresh() {
        super.onTokenRefresh()

        // Just try to register again and update backend with information
        val intent = Intent(this, GCMRegistrationIntentService::class.java)
        startService(intent)
    }
}