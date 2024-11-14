package com.takuron.whisperwavemixer.utils

import android.content.res.Resources
import android.util.TypedValue

object ViewUtils {
    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
    }
}