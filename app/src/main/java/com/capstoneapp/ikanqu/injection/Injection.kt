package com.capstoneapp.ikanqu.injection

import android.content.Context
import com.capstoneapp.ikanqu.network.ApiConfig
import com.capstoneapp.ikanqu.repository.AppRepository

object Injection {
    fun provideRepo(context: Context): AppRepository {
        val apiServices = ApiConfig.getApiServices()
        return AppRepository.getInstance(apiServices)
    }
}