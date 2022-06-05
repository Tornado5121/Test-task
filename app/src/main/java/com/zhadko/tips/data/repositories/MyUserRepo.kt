package com.zhadko.tips.data.repositories

import com.zhadko.tips.models.domainModels.DomainUser
import kotlinx.coroutines.flow.MutableStateFlow

interface MyUserRepo {

    val userNameFlow: MutableStateFlow<String>
    val errorMessageFlow: MutableStateFlow<Int>

    suspend fun loginUser(fullPhoneNumber: String, password: String)
    suspend fun deleteUser()
    suspend fun getUserData(): DomainUser

}