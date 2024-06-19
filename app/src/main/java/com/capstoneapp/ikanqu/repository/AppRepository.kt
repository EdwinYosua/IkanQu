package com.capstoneapp.ikanqu.repository

import com.capstoneapp.ikanqu.local.SettingPreference
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.network.ApiServices
import com.capstoneapp.ikanqu.network.response.LoginSuccess
import com.capstoneapp.ikanqu.network.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepository private constructor(
    private val apiServices: ApiServices,
    private val pref: SettingPreference
) {

    fun register(
        name: String,
        email: String,
        pass: String
    ): Flow<ApiResult<RegisterResponse>> = flow {
        try {
            emit(ApiResult.ApiLoading)
            val response = apiServices.register(
                name,
                email,
                pass,
            )
            if (response.error != true) {
                emit(ApiResult.ApiSuccess(response))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.ApiError(e.message.toString()))
        }
    }

    fun login(email: String, pass: String): Flow<ApiResult<LoginSuccess>> = flow {
        try {
            emit(ApiResult.ApiLoading)
            val response = apiServices.login(email, pass)
            if (response.error != true) {
                val loginSuccess = LoginSuccess(response.name, response.userId, response)
                response.userId?.let {
                    response.name?.let { it1 ->
                        pref.saveUserLoginData(it, it1)
                    }
                }
                emit(ApiResult.ApiSuccess(loginSuccess))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.ApiError(e.message.toString()))
        }
    }

    fun getUserId() = pref.getUserId()
    fun getUserName() = pref.getUserName()

    suspend fun clearLoginData() = pref.clearUserLoginData()


    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiServices: ApiServices,
            pref: SettingPreference
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(
                    apiServices,
                    pref
                ).also { instance = it }
            }
    }

}