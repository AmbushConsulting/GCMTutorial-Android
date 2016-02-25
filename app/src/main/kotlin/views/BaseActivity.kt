package com.ambush.gcmtutorial.views

import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

open class BaseActivity : AppCompatActivity() {

    private fun recreateBackStack() {
        val upIntent = NavUtils.getParentActivityIntent(this)
        if (upIntent == null) return
        TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(upIntent)
                .startActivities()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (isTaskRoot) {
                    recreateBackStack()
                    return true
                }
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (isTaskRoot && NavUtils.getParentActivityIntent(this) != null) {
            recreateBackStack()
            return
        }
        super.onBackPressed()
    }
}