package com.dheaninda.part1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUser {
    private const val BASE_URL = "https://api.github.com/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiInstance: Api = retrofit.create(Api::class.java)
}