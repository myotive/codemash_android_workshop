package com.myotive.codemashschedule.utilities

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by myotive on 12/10/2017.
 */
fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit,
                        failure: (t: Throwable) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>) = success(response)

        override fun onFailure(call: Call<T>?, t: Throwable) = failure(t)
    })
}
