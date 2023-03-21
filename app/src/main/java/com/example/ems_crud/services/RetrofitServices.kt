package com.example.ems_crud.services

import com.example.ems_crud.model.Employee
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitServices {
    @GET("users")
    fun getAllUsers() : Call<List<Employee>>

    @POST("users")
    fun createUser(@Body employee: Employee): Call<Employee>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id:Int, @Body employee: Employee): Call<Employee>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id:Int): Call<Employee>
}