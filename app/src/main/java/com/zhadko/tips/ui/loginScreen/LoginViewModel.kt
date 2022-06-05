package com.zhadko.tips.ui.loginScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.tips.data.repositories.MyUserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val myUserRepo: MyUserRepo
) : ViewModel() {

    private val mUserNameLiveData = MutableLiveData<String>()
    val userNameLiveData = mUserNameLiveData

    private val mErrorMessageLiveData = MutableLiveData<Int>()
    val errorMessageLiveData = mErrorMessageLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            myUserRepo.userNameFlow.collect {
                mUserNameLiveData.postValue(it)
            }
        }

        viewModelScope.launch {
            myUserRepo.errorMessageFlow.collect {
                mErrorMessageLiveData.postValue(it)
            }
        }
    }

    fun login(phoneNumber: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            myUserRepo.loginUser(phoneNumber, password)
        }
    }

}