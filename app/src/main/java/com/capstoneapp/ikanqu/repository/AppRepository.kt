package com.capstoneapp.ikanqu.repository

import ModelResponse
import com.capstoneapp.ikanqu.local.SettingPreference
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.network.ApiServices
import com.capstoneapp.ikanqu.network.ModelApiServices
import com.capstoneapp.ikanqu.network.response.LoginSuccess
import com.capstoneapp.ikanqu.network.response.RegisterResponse
import com.capstoneapp.ikanqu.utils.reduceFileImg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AppRepository private constructor(
    private val apiServices: ApiServices,
    private val modelApiServices: ModelApiServices,
    private val pref: SettingPreference
) {

    fun analyzeImg(imgUri: File): Flow<ApiResult<ModelResponse>> = flow {

        try {
            emit(ApiResult.ApiLoading)

            val img = imgUri.reduceFileImg()
            val reqImgFile = img.asRequestBody("image/*".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData("file", img.name, reqImgFile)
            val response = modelApiServices.predict(multipartBody)

            //add error later
            emit(ApiResult.ApiSuccess(response))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.ApiError(e.message.toString()))
        }
    }

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
            modelApiServices: ModelApiServices,
            pref: SettingPreference
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(
                    apiServices,
                    modelApiServices,
                    pref
                ).also { instance = it }
            }
    }

}