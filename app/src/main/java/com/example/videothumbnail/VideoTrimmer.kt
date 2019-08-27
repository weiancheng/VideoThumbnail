package com.example.videothumbnail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

class VideoTrimmer: RelativeLayout {

    private val TAG = "VideoTrimmer"
    private lateinit var videoThumbRecyclerView: RecyclerView
    private lateinit var videoFrameAdapter: VideoFrameAdapter

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.item_video_trimmer, this, true)
        videoThumbRecyclerView = findViewById(R.id.video_frame_rv)
        videoFrameAdapter = VideoFrameAdapter(context, ResourceUtil.getDisplayWidth(context) / 15)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        videoThumbRecyclerView.layoutManager = linearLayoutManager
        //videoThumbRecyclerView.addItemDecoration(SpacesItemDecoration(1, 14))
        videoThumbRecyclerView.adapter = videoFrameAdapter
    }

    fun showThumbnails() {
        videoThumbRecyclerView.visibility = View.VISIBLE
    }

    fun hideThumbnails() {
        videoThumbRecyclerView.visibility = View.GONE
    }

    fun reverseThumbnails() {
        videoFrameAdapter.reverseItem()
    }

    fun addThumbnail(info: VideoFrameInfo) {
        videoFrameAdapter.addVideoFrame(info)
    }
}