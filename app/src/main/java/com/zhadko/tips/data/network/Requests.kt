package com.zhadko.tips.data.network

import com.zhadko.tips.models.retrofitModels.User
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface Requests {

    @POST("login")
    suspend fun getUser(
        @Query("phone_code") phone_code: String,
        @Query("phone_number") phone_number: String,
        @Query("password") password: String
    ) : Response<User>

}