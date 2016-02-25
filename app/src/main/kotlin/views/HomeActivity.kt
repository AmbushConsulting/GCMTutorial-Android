package com.ambush.gcmtutorial.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.ambush.gcmtutorial.R

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setContentView(R.layout.activity_home)
    }



}
