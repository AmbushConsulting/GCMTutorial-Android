import android.content.Context
import android.preference.PreferenceManager

object PreferencesHelper {

    fun saveGCMToken(context: Context, token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("gcmToken", token).apply()
    }

    fun getGCMToken(context: Context): String {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("gcmToken", "")
    }

    fun setTokenSentToServer(context: Context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("sentTokenToServer", true).apply()
    }

    fun getSubscribedTopics(context: Context): MutableSet<String> {
        var topics = mutableSetOf<String>()
        topics = PreferenceManager.getDefaultSharedPreferences(context).getStringSet("topics", topics)
        return topics
    }

    fun addSubscribedTopic(context: Context, topic: String) {
        val topics = getSubscribedTopics(context)
        topics.add(topic)
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet("topics", topics).apply()
    }

    fun removeSubscribedTopic(context: Context, topic: String) {
        val topics = getSubscribedTopics(context)
        topics.remove(topic)
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet("topics", topics).apply()
    }
}