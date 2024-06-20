package com.capstoneapp.ikanqu.network

import com.capstoneapp.ikanqu.BuildConfig
import com.capstoneapp.ikanqu.local.SettingPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        private fun createClient(pref: SettingPreference): OkHttpClient {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val userId = runBlocking { pref.getUserId().first() }
                val reqHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $userId")
                    .build()
                chain.proceed(reqHeaders)
            }
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
        }

        private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun getApiServices(pref: SettingPreference): ApiServices {
            val client = createClient(pref)
            val retrofit = createRetrofit(BuildConfig.BASE_URL, client)
            return retrofit.create(ApiServices::class.java)
        }

        fun getModelApiServices(pref: SettingPreference): ModelApiServices {
            val client = createClient(pref)
            val retrofit = createRetrofit(BuildConfig.MODEL_URL, client)
            return retrofit.create(ModelApiServices::class.java)
        }


//        fun getApiServices(pref: SettingPreference): ApiServices {
//            val loggingInterceptor = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
//            val authInterceptor = Interceptor { chain ->
//                val req = chain.request()
//                val userId = runBlocking { pref.getUserId().first() }
//                val reqHeaders = req.newBuilder()
//                    .addHeader("Authorization", "Bearer $userId")
//                    .build()
//                chain.proceed(reqHeaders)
//            }
//            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .addInterceptor(authInterceptor)
//                .build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//            return retrofit.create(ApiServices::class.java)
//        }
    }
}