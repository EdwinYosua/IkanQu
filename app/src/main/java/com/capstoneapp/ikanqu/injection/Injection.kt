package com.capstoneapp.ikanqu.injection

import android.content.Context
import com.capstoneapp.ikanqu.local.SettingPreference
import com.capstoneapp.ikanqu.local.dataStore
import com.capstoneapp.ikanqu.network.ApiConfig
import com.capstoneapp.ikanqu.repository.AppRepository

object Injection {
    fun provideRepo(context: Context): AppRepository {
        val pref = SettingPreference.getInstance(context.dataStore)
        val apiServices = ApiConfig.getApiServices(pref)
        val modelApiServices = ApiConfig.getModelApiServices(pref)
        return AppRepository.getInstance(apiServices, modelApiServices, pref)
    }
}