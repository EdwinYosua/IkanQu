package com.capstoneapp.ikanqu

import android.content.Context
import com.capstoneapp.ikanqu.network.ApiConfig

object Injection {
    fun provideRepo(context: Context):AppRepository {
        val apiServices = ApiConfig.getApiServices()
        return AppRepository.getInstance(apiServices)
    }
}