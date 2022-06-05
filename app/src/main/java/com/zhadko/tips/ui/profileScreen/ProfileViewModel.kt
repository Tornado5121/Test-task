package com.zhadko.tips.ui.profileScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.tips.data.repositories.MyUserRepo
import com.zhadko.tips.models.domainModels.DomainUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val myUserRepo: MyUserRepo
) : ViewModel() {

    private val mMyUserLiveData = MutableLiveData<DomainUser>()
    val myUserLiveData = mMyUserLiveData

    fun getMyUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            mMyUserLiveData.postValue(myUserRepo.getUserData())
        }
    }

}