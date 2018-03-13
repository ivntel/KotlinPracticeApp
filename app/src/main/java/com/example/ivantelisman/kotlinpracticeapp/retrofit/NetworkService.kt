package com.example.ivantelisman.kotlinpracticeapp.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivantelisman on 2/8/18.
 */
class NetworkService private constructor() {
    private val networkService: NetworkApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder().baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        networkService = retrofit.create<NetworkApi>(NetworkApi::class.java!!)
    }

    companion object {
        private val API_BASE_URL = "https://jsonplaceholder.typicode.com/"
        private var networkServiceClient: NetworkService? = null
        val serviceInstance: NetworkApi
            get() {
                if (networkServiceClient == null) {
                    networkServiceClient = NetworkService()
                }
                return networkServiceClient!!.networkService
            }
    }
}