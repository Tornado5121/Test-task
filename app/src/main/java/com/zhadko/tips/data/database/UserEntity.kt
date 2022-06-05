package com.zhadko.tips.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhadko.tips.models.domainModels.DomainUser

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val surname: String,
    val name: String,
    val phoneNumber: String
)


fun UserEntity.asDomainModel(): DomainUser {
    return DomainUser(
        surname = surname,
        name = name,
        phoneNumber = phoneNumber
    )
}
