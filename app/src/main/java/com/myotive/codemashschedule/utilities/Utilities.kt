package com.myotive.codemashschedule.utilities

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myotive.codemashschedule.R
import com.myotive.codemashschedule.api.entity.SessionData
import java.io.IOException

/**
 * Created by myotive on 12/10/2017.
 */

fun loadCodeMashResponseFromAssets(activity : Activity, gson : Gson ) : List<SessionData>?{
    val inputStream = activity.resources.openRawResource(R.raw.sample_codemash_response)
    var response : List<SessionData>
    try {
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        val jsonString = String(bytes)

        val sessionDataType = object : TypeToken<List<SessionData>>() {}.type

        response = gson.fromJson<List<SessionData>>(jsonString, sessionDataType)

    } catch (e: IOException) {
        return null
    }

    return response
}