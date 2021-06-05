package com.android.taskfriday.api


import com.android.taskfriday.model.InputFieldModel
import retrofit2.Response
import retrofit2.http.GET


interface NewsApi {

    @GET("0ab55618-e96b-4a47-bd2a-1cd834fa72d0")
    suspend fun getRequest(): Response<List<List<InputFieldModel>>>

}