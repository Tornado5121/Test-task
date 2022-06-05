package com.zhadko.tips.data.network

import com.zhadko.tips.data.repositories.Authentication
import com.zhadko.tips.models.domainModels.DomainUser
import com.zhadko.tips.models.retrofitModels.User
import retrofit2.Response

class AuthenticationImpl(private val api: Requests) : Authentication {

    override suspend fun loginUser(
        phoneCode: String,
        phoneNumber: String,
        password: String
    ): Response<User> {
        return api.getUser(phoneCode, phoneNumber, password)
    }

}