package com.zhadko.tips.data.repositories

import com.zhadko.tips.models.domainModels.DomainUser
import com.zhadko.tips.models.retrofitModels.asDomainModel
import kotlinx.coroutines.flow.MutableStateFlow

const val myUserId = 1
const val firstSymbolOfCountryCode = 0
const val lastSymbolOfCountryCode = 4
const val firstSymbolOfPhoneNumber = 4
const val lastSymbolOfPhoneCode = 13
const val noInternetConnectionCode = 499

class AuthRepo(
    private val userDataRepo: UserDataRepo,
    private val authentication: Authentication
) : MyUserRepo {

    private val mUserNameFlow = MutableStateFlow("")
    override val userNameFlow = mUserNameFlow

    private val mErrorMessageFlow = MutableStateFlow(-1)
    override val errorMessageFlow = mErrorMessageFlow

    override suspend fun loginUser(fullPhoneNumber: String, password: String) {
        val phoneCode = fullPhoneNumber.substring(firstSymbolOfCountryCode, lastSymbolOfCountryCode)
        val phoneNumber = fullPhoneNumber.substring(firstSymbolOfPhoneNumber, lastSymbolOfPhoneCode)
        try {
            val myUser = authentication.loginUser(phoneCode, phoneNumber, password)
            if (myUser.isSuccessful && myUser.code() == 200) {
                myUser.body()?.asDomainModel()
                deleteUser()
                userDataRepo.saveUserData(myUser.body()?.asDomainModel() ?: DomainUser("", "", ""))
                mUserNameFlow.emit(myUser.body()?.user?.name ?: "")
            } else {
                mErrorMessageFlow.emit(myUser.code())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessageFlow.emit(noInternetConnectionCode)
        }
    }

    override suspend fun deleteUser() {
        userDataRepo.deleteUser()
    }

    override suspend fun getUserData(): DomainUser {
        return userDataRepo.getUserData(myUserId)
    }

}

