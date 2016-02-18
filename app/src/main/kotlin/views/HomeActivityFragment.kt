package com.ambush.gcmtutorial.views

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ambush.gcmtutorial.R
import com.ambush.gcmtutorial.gcm.GCMRegistrationIntentService
import com.ambush.gcmtutorial.gcm.GCMTopicSubscriber
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import kotlinx.android.synthetic.main.fragment_home.*

class HomeActivityFragment : Fragment() {

    val TAG = "HomeActivityFragment"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToGCM()
        setupButtons()
    }

    private fun setupButtons() {
        homeCityNewsButton.setOnClickListener { toggleCityNewsRegistration() }
        homeStateNewsButton.setOnClickListener { toggleStateNesRegistration() }
    }

    private fun toggleCityNewsRegistration() {
        val isSubscribed = GCMTopicSubscriber().toggleTopicSubscription(context, "city")
        homeCityNewsButton.text = if (isSubscribed) "Unsubscribe to city news" else "Subscribe to city news"
    }

    private fun toggleStateNesRegistration() {
        val isSubscribed = GCMTopicSubscriber().toggleTopicSubscription(context, "state")
        homeStateNewsButton.text = if (isSubscribed) "Unsubscribe to state news" else "Subscribe to state news"
    }

    private fun registerToGCM() {
        if (!hasConfiguredPush()) {
            if (checkPlayServices()) {
                val intent = Intent(context, GCMRegistrationIntentService::class.java)
                context.startService(intent)
            }
        } else {
            logMessage("GCM Token - ${getToken()}")
        }
    }

    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(activity)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, 9000).show()
            } else {
                logMessage("This device is not supported.")
                activity.finish()
            }
            return false
        }
        return true
    }

    private fun logMessage(message: String) {
        Log.i(TAG, message)
    }

    private fun showMessage(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    private fun getToken(): String {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString("gcmToken", "")
    }

    private fun hasConfiguredPush(): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("sentTokenToServer", false)
    }
}
