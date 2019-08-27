package com.example.videothumbnail

import android.graphics.Bitmap

class VideoFrameInfo(time: Long, bitmap: Bitmap) {
    var time: Long = 0
    var bitmap: Bitmap

    init {
        this.time = time
        this.bitmap = bitmap
    }
}