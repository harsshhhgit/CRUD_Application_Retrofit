package com.example.ems_crud.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ems_crud.repository.EmployeeRepository

class EmployeeViewModelFactory(val employeeRepository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeViewModel(employeeRepository) as T
    }

}