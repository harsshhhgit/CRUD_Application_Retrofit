package com.example.ems_crud.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ems_crud.model.Employee
import com.example.ems_crud.repository.EmployeeRepository

class EmployeeViewModel(val employeeRepository: EmployeeRepository): ViewModel() {
//    fun getAllUsers(){
//        employeeRepository.getAllUsers()
//    }
    val usersLiveData : MutableLiveData<List<Employee>> = employeeRepository.getAllUsers()
}