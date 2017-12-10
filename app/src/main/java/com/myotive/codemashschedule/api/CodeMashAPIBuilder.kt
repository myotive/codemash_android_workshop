package com.myotive.codemashschedule.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by myotive on 12/9/2017.
 */
class CodeMashAPIBuilder {
    companion object {
        fun getGSON(): Gson {
            return GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()
        }

        fun build(): CodeMashAPI {
            // Build logging interceptor so we can see http requests/responses
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            // Build HTTP Client, which will make the calls to web services
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            // Build GSON configuration to handle deseralizing responses from web services
            val gson = getGSON()

            // Build Retrofit to glue OKHttp and GSON together and create API implementations
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://speakers.codemash.org/api/")
                    .build()

            return retrofit.create(CodeMashAPI::class.java)
        }
    }
}