package com.capstoneapp.ikanqu.network

import ModelResponse
import okhttp3.MultipartBody
import retrofit2.http.POST
import retrofit2.http.Part

interface ModelApiServices {
    @POST("predict")
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): ModelResponse
}