package com.myotive.codemashschedule.api

import com.myotive.codemashschedule.api.entity.SessionData
import retrofit2.Call
import retrofit2.http.GET

/**
 * CodeMash Retrofit API
 * Contains methods to call CodeMash Web Services
 */
interface CodeMashAPI {
    @GET("sessionsdata")
    fun SessionData(): Call<List<SessionData>>
}