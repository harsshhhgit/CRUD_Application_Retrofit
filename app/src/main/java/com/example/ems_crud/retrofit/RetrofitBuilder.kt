package com.example.ems_crud.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val URL = "https://mockdatahub-production.up.railway.app/"

    fun getInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}