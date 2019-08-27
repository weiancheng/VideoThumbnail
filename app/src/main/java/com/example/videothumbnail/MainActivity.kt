package com.example.videothumbnail

import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = "VideoTrimmer"
    private lateinit var videoTrimmer: VideoTrimmer
    private val VIDEO_FILE = "SampleVideo_1280x720_5mb.mp4"
    private val startPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoTrimmer = findViewById(R.id.video_trimmer)
        val fileDescriptor = this.assets.openFd(VIDEO_FILE)
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(fileDescriptor.fileDescriptor, fileDescriptor.startOffset, fileDescriptor.length)
        val videoLength = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toLong()
        val interval = (videoLength - startPosition) / 14
        var thumbnailCount = videoLength / 1000
        if ((videoLength.toInt() % 1000) != 0) {
            thumbnailCount.inc()
        }
        thumbnailCount -= 1
        for (i in 0 until thumbnailCount) {
            var bitmap: Bitmap?
            val time = startPosition + (interval * i)
            Log.i(TAG, "time: $time")
            if (i == thumbnailCount) {
                if (interval > 1000) {
                    bitmap = extractFrame(metadataRetriever, videoLength - 800)
                    videoTrimmer.addThumbnail(VideoFrameInfo(videoLength - 800, bitmap!!))
                } else {
                    bitmap = extractFrame(metadataRetriever, videoLength)
                    videoTrimmer.addThumbnail(VideoFrameInfo(videoLength, bitmap!!))
                }
            } else {
                bitmap = extractFrame(metadataRetriever, time)
                videoTrimmer.addThumbnail(VideoFrameInfo(time, bitmap!!))
            }
        }
    }

    private fun extractFrame(metadataRetriever: MediaMetadataRetriever, time: Long): Bitmap? {
        val bitmap = metadataRetriever.getFrameAtTime(time * 1000, MediaMetadataRetriever.OPTION_CLOSEST)
        bitmap?: run {
            return null
        }

        val bitmapNew = scaleImage(bitmap)
        bitmapNew?.let {
            /*if (it.isRecycled.not())
                it.recycle()*/
        }
        return bitmapNew
    }

    private fun scaleImage(bitmap: Bitmap?): Bitmap? {
        bitmap?.let {
            val width = it.width
            val height = it.height
            val scaleWidth = ((ResourceUtil.getDisplayWidth(this) / 4) * 1.0f) / width
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleWidth)
            val newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
            /*if (newBitmap.isRecycled.not())
                newBitmap.recycle()*/
            return newBitmap
        }
        return null
    }
}
