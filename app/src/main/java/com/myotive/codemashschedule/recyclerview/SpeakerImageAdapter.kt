package com.myotive.codemashschedule.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.myotive.codemashschedule.R
import com.myotive.codemashschedule.api.entity.Speaker
import kotlinx.android.synthetic.main.item_speaker_image.view.*

/**
 * Adapter class of SessionData objects to be consumed by RecyclerView
 */
class SpeakerImageAdapter(private var items : List<Speaker> = mutableListOf())
    : RecyclerView.Adapter<SpeakerImageAdapter.ViewHolder>() {

    /**
     * Create ViewHolder - inflate layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speaker_image, parent, false)

        return ViewHolder(view)
    }

    /**
     * Bind ViewHolder to Item based on where we are in the RecyclerView
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    /**
     * Get total count of items in our list.
     */
    override fun getItemCount() = items.size


    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        fun bind(item : Speaker) = with(view){

            if(item.gravatarUrl != null) {
                // The Default image size for gravatar images is 80px. Let's get a bigger image
                // for this recyclerview.
                Glide.with(context)
                        .load("http:" + item.gravatarUrl + "?s=350")
                        .placeholder(R.drawable.ic_codemash)
                        .into(iv_item_speaker_image)
            }
        }
    }
}