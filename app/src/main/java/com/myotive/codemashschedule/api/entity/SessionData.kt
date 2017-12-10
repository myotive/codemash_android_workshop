package com.myotive.codemashschedule.api.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlinx.android.parcel.Parcelize
import java.util.*


/**
 * Created by myotive on 12/9/2017.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class SessionData(

        @SerializedName("Id")
        @Expose
        var id: Int,
        @SerializedName("SessionTime")
        @Expose
        var sessionTime: Date? = null,
        @SerializedName("SessionStartTime")
        @Expose
        var sessionStartTime: Date? = null,
        @SerializedName("SessionEndTime")
        @Expose
        var sessionEndTime: Date? = null,
        @SerializedName("Room")
        @Expose
        var room: String? = null,
        @SerializedName("Rooms")
        @Expose
        var rooms: List<String> = listOf(),
        @SerializedName("Title")
        @Expose
        var title: String? = null,
        @SerializedName("Abstract")
        @Expose
        var abstract: String? = null,
        @SerializedName("SessionType")
        @Expose
        var sessionType: String? = null,
        @SerializedName("Tags")
        @Expose
        var tags: List<String> = listOf(),
        @SerializedName("Category")
        @Expose
        var category: String? = null,
        @SerializedName("Speakers")
        @Expose
        var speakers: List<Speaker> = listOf()
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Speaker(
        @SerializedName("Id")
        @Expose
        var id: String,
        @SerializedName("FirstName")
        @Expose
        var firstName: String? = null,
        @SerializedName("LastName")
        @Expose
        var lastName: String? = null,
        @SerializedName("GravatarUrl")
        @Expose
        var gravatarUrl: String? = null
) : Parcelable