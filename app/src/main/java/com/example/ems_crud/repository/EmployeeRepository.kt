package com.example.ems_crud.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ems_crud.adapter.EmployeeAdapter
import com.example.ems_crud.model.Employee
import com.example.ems_crud.services.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository(val retrofitServices: RetrofitServices) {

    //    lateinit var usersLiveData : MutableLiveData<List<Employee>>
    fun getAllUsers(): MutableLiveData<List<Employee>> {
        var usersLiveData = MutableLiveData<List<Employee>>()
        val result = retrofitServices.getAllUsers()
        result.enqueue(object : Callback<List<Employee>?> {
            override fun onResponse(
                call: Call<List<Employee>?>,
                response: Response<List<Employee>?>
            ) {
                Log.d("retrofit", "Retrieve - ${response.body()}")
                usersLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Employee>?>, t: Throwable) {
                Log.d("retrofit", "Failed ${t.message}")
            }

        })
        return usersLiveData
    }

}