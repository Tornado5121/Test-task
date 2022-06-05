package com.zhadko.tips.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zhadko.tips.models.domainModels.DomainUser

@Dao
interface UserDao {

    @Insert
    fun addUser(userEntity: UserEntity)

    @Query("SELECT * from UserEntity where id = :id")
    fun getUser(id: Int): UserEntity

    @Query("DELETE from UserEntity")
    fun deleteUser()

}