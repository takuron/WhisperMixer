package com.takuron.whisperwavemixer.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.startActivity(mclass:Class<out Activity>, bundle: Bundle? = null) {
    val intent = Intent(this, mclass)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}