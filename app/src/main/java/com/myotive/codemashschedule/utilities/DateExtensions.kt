package com.myotive.codemashschedule.utilities

import android.content.Context
import android.text.format.DateFormat
import java.util.*

/**
 * Created by myotive on 12/10/2017.
 */
fun Date.toShortDate(context : Context) : String {
    return DateFormat.getDateFormat(context).format(this)
}

fun Date.toTime(context : Context) : String {
    return DateFormat.getTimeFormat(context).format(this)
}