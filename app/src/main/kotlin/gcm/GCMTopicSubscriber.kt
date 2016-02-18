package com.ambush.gcmtutorial.gcm

import PreferencesHelper
import android.content.Context
import android.os.AsyncTask
import com.google.android.gms.gcm.GcmPubSub

class GCMTopicSubscriber {

    private fun getSubscribedTopics(context: Context): Set<String> {
        return PreferencesHelper.getSubscribedTopics(context)
    }

    private fun addSubscribedTopic(context: Context, topic: String) {
        PreferencesHelper.addSubscribedTopic(context, topic)
    }

    private fun removeSubscribedTopic(context: Context, topic: String) {
        PreferencesHelper.removeSubscribedTopic(context, topic)
    }

    fun toggleTopicSubscription(context: Context, topic: String): Boolean {
        val topics = getSubscribedTopics(context)
        if (topics.contains(topic)) {
            unSubscribeToTopic(context, PreferencesHelper.getGCMToken(context), "/topics/$topic")
            removeSubscribedTopic(context, topic)
            return true
        } else {
            subscribeToTopic(context, PreferencesHelper.getGCMToken(context), "/topics/$topic")
            addSubscribedTopic(context, topic)
            return false
        }
    }

    private fun unSubscribeToTopic(context: Context, gcm: String, topic: String) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void): Void? {
                val gcmPubSub = GcmPubSub.getInstance(context)
                gcmPubSub.unsubscribe(gcm, topic)
                return null
            }
        }.execute()
    }

    private fun subscribeToTopic(context: Context, gcm: String, topic: String) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void): Void? {
                val gcmPubSub = GcmPubSub.getInstance(context)
                gcmPubSub.subscribe(gcm, topic, null)
                return null
            }
        }.execute()
    }

}

