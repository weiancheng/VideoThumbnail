package com.example.videothumbnail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class VideoFrameAdapter(private val context: Context, private val itemWidth: Int): RecyclerView.Adapter<VideoFrameAdapter.VideoFrameHolder>() {

    private var videoFrameBuffer = ArrayList<VideoFrameInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoFrameHolder {
        return VideoFrameHolder(LayoutInflater.from(context).inflate(R.layout.item_video_frame, parent, false))
    }

    override fun onBindViewHolder(holder: VideoFrameHolder, position: Int) {
        //Glide.with(context).load(videoFrameBuffer[position].bitmap).into(holder.img)
        holder.img.setImageBitmap(videoFrameBuffer[position].bitmap)
        Log.i("VideoTrimmer", "width: ${holder.img.width}, height: ${holder.img.height}, bitmap width: ${videoFrameBuffer[position].bitmap.width}, bitmap height: ${videoFrameBuffer[position].bitmap.height}")
    }

    override fun getItemCount() = videoFrameBuffer.size

    fun addVideoFrame(videoFrameInfo: VideoFrameInfo) {
        videoFrameBuffer.add(videoFrameInfo)
        notifyItemInserted(videoFrameBuffer.size)
    }

    fun reverseItem() {
        videoFrameBuffer.reverse()
        notifyDataSetChanged()
    }

    inner class VideoFrameHolder internal constructor(view: View): RecyclerView.ViewHolder(view) {
        var img = view.findViewById(R.id.video_frame) as ImageView

        init {
            val layoutParams = img.layoutParams
            layoutParams.width = itemWidth
            img.layoutParams = layoutParams
        }
    }
}