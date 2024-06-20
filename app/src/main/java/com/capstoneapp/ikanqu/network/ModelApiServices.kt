package com.capstoneapp.ikanqu.network

import com.capstoneapp.ikanqu.network.response.ModelResponse
import retrofit2.http.Field
import retrofit2.http.POST
import java.io.File

interface ModelApiServices {
    @POST("register")
    suspend fun predict(
        @Field("file") file: File
    ): ModelResponse
}