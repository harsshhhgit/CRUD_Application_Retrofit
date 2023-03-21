package com.example.ems_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.ems_crud.adapter.EmployeeAdapter
import com.example.ems_crud.model.Employee
import com.example.ems_crud.retrofit.RetrofitBuilder
import com.example.ems_crud.services.RetrofitServices
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        supportActionBar!!.title = "Create User"
        val retrofitBuilder = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)

        var id = findViewById<TextInputEditText>(R.id.createId)
        var firstName = findViewById<TextInputEditText>(R.id.createFirstName)
        var lastName = findViewById<TextInputEditText>(R.id.createLastName)
        var emailId = findViewById<TextInputEditText>(R.id.createEmail)
        var imageURL = findViewById<TextInputEditText>(R.id.createImageUrl)
        var phoneNumber = findViewById<TextInputEditText>(R.id.createPhoneNumber)

        val deptRadioGrp = findViewById<RadioGroup>(R.id.deptradio)
        lateinit var selectedDepartmentRadioBtn: RadioButton
        lateinit var selectedDepartmentRadioBtnValue: String
        val desgRadioGroup = findViewById<RadioGroup>(R.id.desiradio)
        lateinit var selectedDesignationRadioBtn: RadioButton
        lateinit var selectedDesignationRadioBtnValue: String
        val statusRadioGroup = findViewById<RadioGroup>(R.id.statusradio)
        lateinit var selectedStatusRadioBtn: RadioButton
        lateinit var selectedStatusRadioBtnValue: String
        val positionRadioGroup = findViewById<RadioGroup>(R.id.positionradio)
        lateinit var selectedPositionRadioBtn: RadioButton
        lateinit var selectedPositionRadioBtnValue: String
        val bgRadioGroup = findViewById<RadioGroup>(R.id.bgradio)
        lateinit var selectedBloodGrpRadioBtn: RadioButton
        lateinit var selectedBloodGrpRadioBtnValue: String

        val createEmpBtn = findViewById<Button>(R.id.createEmp)
        createEmpBtn.setOnClickListener {
            val selectedDepartmentRadioButtonId = deptRadioGrp.checkedRadioButtonId
            val selectedDesignationtRadioButtonId = desgRadioGroup.checkedRadioButtonId
            val selectedStatusRadioButtonId = statusRadioGroup.checkedRadioButtonId
            val selectedPositionRadioButtonId = positionRadioGroup.checkedRadioButtonId
            val selectedBloodGroupRadioButtonId = bgRadioGroup.checkedRadioButtonId

            if(selectedDepartmentRadioButtonId != -1){
                selectedDepartmentRadioBtn = findViewById(selectedDepartmentRadioButtonId)
                selectedDepartmentRadioBtnValue = selectedDepartmentRadioBtn.text.toString()
            }
            if(selectedDesignationtRadioButtonId != -1){
                selectedDesignationRadioBtn = findViewById(selectedDesignationtRadioButtonId)
                selectedDesignationRadioBtnValue = selectedDesignationRadioBtn.text.toString()
            }
            if(selectedStatusRadioButtonId != -1){
                selectedStatusRadioBtn = findViewById(selectedStatusRadioButtonId)
                selectedStatusRadioBtnValue = selectedStatusRadioBtn.text.toString()
            }
            if(selectedPositionRadioButtonId != -1){
                selectedPositionRadioBtn = findViewById(selectedPositionRadioButtonId)
                selectedPositionRadioBtnValue = selectedPositionRadioBtn.text.toString()
            }
            if(selectedBloodGroupRadioButtonId != -1){
                selectedBloodGrpRadioBtn = findViewById(selectedBloodGroupRadioButtonId)
                selectedBloodGrpRadioBtnValue = selectedBloodGrpRadioBtn.text.toString()
            }
//            detailView.text = firstName.text.toString()+ " " + lastName.text.toString() + " " + phoneNumber.text.toString()+" " +
//                    emailId.text.toString() + " "+ imageURL.text.toString() + " "+selectedBloodGrpRadioBtnValue + " "+ selectedDepartmentRadioBtnValue+
//                    " " + selectedDesignationRadioBtnValue+ " "+ selectedPositionRadioBtnValue+ " "+ selectedStatusRadioBtnValue

            val retrofitResponse = retrofitBuilder.createUser(Employee(_id = (id.text.toString()).toInt(), firstName = firstName.text.toString(), lastName = lastName.text.toString(),
            emailId = emailId.text.toString(), imageUrl = imageURL.text.toString(), phoneNumber = phoneNumber.text.toString(),
            designation = selectedDesignationRadioBtnValue, department = selectedDepartmentRadioBtnValue, status=selectedStatusRadioBtnValue,
            position = selectedPositionRadioBtnValue, bloodGroup = selectedBloodGrpRadioBtnValue))

            Log.d("retrofit", "$retrofitResponse")
            retrofitResponse.enqueue(object : Callback<Employee?> {
                override fun onResponse(call: Call<Employee?>, response: Response<Employee?>) {
                    Log.d("retrofit", "Create - Status Code: ${response.code()}, Body: ${response.body()}")
                    Toast.makeText(this@CreateActivity, "Employee Successfully added", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@CreateActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Employee?>, t: Throwable) {
                    Log.d("retrofit", "${t.message}")
                    Toast.makeText(this@CreateActivity, "Error Encountered", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this@CreateActivity, MainActivity::class.java)
        startActivity(intent)
    }
}