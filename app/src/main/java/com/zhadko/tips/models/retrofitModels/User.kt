package com.zhadko.tips.models.retrofitModels

import com.zhadko.tips.models.domainModels.DomainUser

data class User(
    val status: String,
    val user: UserX
)

fun User.asDomainModel(): DomainUser {
    return DomainUser(
        surname = user.second_name,
        name = user.name,
        phoneNumber = user.phone_code + user.phone_number
    )
}