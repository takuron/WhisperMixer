package com.takuron.whisperwavemixer.utils

import android.util.Log

object LogUtils {
    const val MY_DEBUG_KEY = "takuronmono_"

    fun logE(key:String,value:String){
        Log.e("$MY_DEBUG_KEY$key",value)
    }
    fun logW(key:String,value:String){
        Log.w("$MY_DEBUG_KEY$key",value)
    }
}