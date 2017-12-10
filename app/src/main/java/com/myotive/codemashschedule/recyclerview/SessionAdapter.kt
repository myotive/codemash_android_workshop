package com.myotive.codemashschedule.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.myotive.codemashschedule.R
import com.myotive.codemashschedule.api.entity.SessionData
import kotlinx.android.synthetic.main.item_sessiondata.view.*

/**
 * Adapter class of SessionData objects to be consumed by RecyclerView
 */
class SessionAdapter(private var items : List<SessionData> = mutableListOf(), private val listener: (SessionData) -> Unit)
    : RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    /**
     * Create ViewHolder - inflate layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sessiondata, parent, false)

        return ViewHolder(view, listener)
    }

    /**
     * Bind ViewHolder to Item based on where we are in the RecyclerView
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    /**
     * Get total count of items in our list.
     */
    override fun getItemCount() = items.size

    /**
     * Swaps current set of items in our adapter. Remember to call notifyDataSetChanged to update
     * RecyclerView
     */
    fun swapData(items : List<SessionData> ){
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val view : View,
                     private val listener: (SessionData) -> Unit) : RecyclerView.ViewHolder(view){
        fun bind(item : SessionData) = with(view){

            setOnClickListener{ listener(item) }

            session_title.text = item.title
            session_desc.text = item.abstract

            // Note: the joinToString extension method can accept a function as the last parameter,
            // it can be called like a function. In this case, the transform is the last parameter.
            // We want to transform the speakers text to include all the speakers names (first name and
            // last name) separated by comma
            // https://kotlinlang.org/docs/reference/lambdas.html
            session_author.text = item.speakers.joinToString(", ") {
                speaker  -> speaker.firstName + " " + speaker.lastName
            }

            if(item.speakers.isNotEmpty()) {
                Glide.with(context)
                        .load("http:" + item.speakers[0].gravatarUrl)
                        .placeholder(R.drawable.ic_codemash)
                        .into(img_speaker)
            }
        }
    }
}