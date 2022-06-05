package com.zhadko.tips.di

import com.zhadko.tips.data.database.UserDatabase
import com.zhadko.tips.data.database.UserDatabaseRepo
import com.zhadko.tips.data.network.AuthenticationImpl
import com.zhadko.tips.data.network.RetrofitClient.api
import com.zhadko.tips.data.repositories.AuthRepo
import com.zhadko.tips.data.repositories.Authentication
import com.zhadko.tips.data.repositories.MyUserRepo
import com.zhadko.tips.data.repositories.UserDataRepo
import com.zhadko.tips.ui.loginScreen.LoginViewModel
import com.zhadko.tips.ui.profileScreen.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single<Authentication> { AuthenticationImpl(api) }
    single<UserDataRepo> { UserDatabaseRepo(UserDatabase.getInstance(androidContext()).userDao) }
    single<MyUserRepo> { AuthRepo(get(), get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}