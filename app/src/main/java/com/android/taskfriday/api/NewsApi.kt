package com.android.taskfriday.api


import com.android.taskfriday.User
import retrofit2.Response
import retrofit2.http.GET


interface NewsApi {

    @GET("d531f5f5-180d-4364-bae7-791dae87f732")
    suspend fun getRequest(): Response<List<List<User>>>

}