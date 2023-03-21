package com.example.ems_crud.model

data class Employee(
    var _id: Int,
    var firstName: String,
    var lastName: String,
    var emailId: String,
    var imageUrl: String,
    var designation: String,
    var department: String,
    var status: String,
    var position: String,
    var bloodGroup: String,
    var phoneNumber: String
)