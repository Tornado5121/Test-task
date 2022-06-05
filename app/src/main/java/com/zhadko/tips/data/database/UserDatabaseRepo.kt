package com.zhadko.tips.data.database

import com.zhadko.tips.data.repositories.UserDataRepo
import com.zhadko.tips.models.domainModels.DomainUser
import com.zhadko.tips.models.domainModels.asDataBaseModel

class UserDatabaseRepo(
    private val userDao: UserDao
) : UserDataRepo {

    override suspend fun saveUserData(user: DomainUser) {
        userDao.addUser(user.asDataBaseModel())
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }

    override suspend fun getUserData(id: Int): DomainUser {
        return userDao.getUser(id).asDomainModel()
    }

}