package com.capstoneapp.ikanqu

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneapp.ikanqu.injection.Injection
import com.capstoneapp.ikanqu.repository.AppRepository
import com.capstoneapp.ikanqu.ui.home.HomeViewModel
import com.capstoneapp.ikanqu.ui.login.LoginViewModel
import com.capstoneapp.ikanqu.ui.main.MainViewModel
import com.capstoneapp.ikanqu.ui.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val appRepo: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(appRepo) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(appRepo) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(appRepo) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(appRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class : ${modelClass.name}")
    }


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepo(context),
//                pref = SettingPreferences.getInstance(context.dataStore)
            )
        }.also {
            instance = it

        }
    }
}