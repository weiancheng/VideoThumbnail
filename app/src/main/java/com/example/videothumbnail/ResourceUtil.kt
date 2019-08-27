package com.example.videothumbnail

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object ResourceUtil {
    fun getDisplayWidth(context: Context): Int {
        val metric = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }
}