package com.zhadko.tips.data.repositories

import com.zhadko.tips.models.retrofitModels.User
import retrofit2.Response

interface Authentication {

    suspend fun loginUser(
        phoneCode: String,
        phoneNumber: String,
        password: String
    ): Response<User>

}