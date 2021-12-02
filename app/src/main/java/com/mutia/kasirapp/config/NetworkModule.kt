package com.mutia.kasirapp.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun interceptor(): OkHttpClient {
        val inteceptor  = HttpLoggingInterceptor()
        inteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(inteceptor).build()
    }

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("http://demo73.energeek.co.id/kasir-app/public/").client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}