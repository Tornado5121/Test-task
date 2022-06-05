package com.zhadko.tips.models.domainModels

import com.zhadko.tips.data.database.UserEntity

data class DomainUser(
    val surname: String,
    val name: String,
    val phoneNumber: String
)

fun DomainUser.asDataBaseModel(): UserEntity {
    return UserEntity(
        id = 1,
        surname = surname,
        name = name,
        phoneNumber = phoneNumber
    )
}