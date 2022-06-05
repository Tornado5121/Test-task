package com.zhadko.tips.data.repositories

import com.zhadko.tips.models.domainModels.DomainUser

interface UserDataRepo {

    suspend fun saveUserData(user: DomainUser)
    suspend fun deleteUser()
    suspend fun getUserData(id: Int): DomainUser

}