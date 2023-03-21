package com.example.ems_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.ems_crud.model.Employee
import com.example.ems_crud.retrofit.RetrofitBuilder
import com.example.ems_crud.services.RetrofitServices
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar!!.title = "Update Employee"
        val updateEmployee = findViewById<Button>(R.id.updateEmployee)
        val retrofitBuilder = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)

        val sp = this.getSharedPreferences("EMS_CRUD", MODE_PRIVATE)
        val idSharedPre = sp.getInt("update_id", 0)

        val _id = findViewById<TextInputEditText>(R.id.updateId)
        val firstName = findViewById<TextInputEditText>(R.id.updateFirstName)
        val lastName = findViewById<TextInputEditText>(R.id.updateLastName)
        val emailId = findViewById<TextInputEditText>(R.id.updateEmail)
        val imageURL = findViewById<TextInputEditText>(R.id.updateImageUrl)
        val phoneNumber = findViewById<TextInputEditText>(R.id.updatePhoneNumber)
        val deptRadioGrp = findViewById<RadioGroup>(R.id.updateDepartmentRadio)
        lateinit var selectedDepartmentRadioBtn: RadioButton
        lateinit var selectedDepartmentRadioBtnValue: String
        val desgRadioGroup = findViewById<RadioGroup>(R.id.updateDesignationRadio)
        lateinit var selectedDesignationRadioBtn: RadioButton
        lateinit var selectedDesignationRadioBtnValue: String
        val statusRadioGroup = findViewById<RadioGroup>(R.id.updateStatusRadio)
        lateinit var selectedStatusRadioBtn: RadioButton
        lateinit var selectedStatusRadioBtnValue: String
        val positionRadioGroup = findViewById<RadioGroup>(R.id.updatePositionRadio)
        lateinit var selectedPositionRadioBtn: RadioButton
        lateinit var selectedPositionRadioBtnValue: String
        val bgRadioGroup = findViewById<RadioGroup>(R.id.updateBgRadio)
        lateinit var selectedBloodGrpRadioBtn: RadioButton
        lateinit var selectedBloodGrpRadioBtnValue: String


        _id.setText(intent.getIntExtra("_id", 0).toString())
        firstName.setText(intent.getStringExtra("firstName"))
        lastName.setText(intent.getStringExtra("lastName"))
        emailId.setText(intent.getStringExtra("emailId"))
        imageURL.setText(intent.getStringExtra("imageUrl"))
        phoneNumber.setText(intent.getStringExtra("phoneNumber"))

        for (i in 0 until deptRadioGrp.childCount) {
            val radioButton = deptRadioGrp.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == intent.getStringExtra("department")) {
                radioButton.isChecked = true
                break
            }
        }
        for (i in 0 until desgRadioGroup.childCount) {
            val radioButton = desgRadioGroup.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == intent.getStringExtra("designation")) {
                radioButton.isChecked = true
                break
            }
        }
        for (i in 0 until statusRadioGroup.childCount) {
            val radioButton = statusRadioGroup.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == intent.getStringExtra("status")) {
                radioButton.isChecked = true
                break
            }
        }
        for (i in 0 until positionRadioGroup.childCount) {
            val radioButton = positionRadioGroup.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == intent.getStringExtra("position")) {
                radioButton.isChecked = true
                break
            }
        }
        for (i in 0 until bgRadioGroup.childCount) {
            val radioButton = bgRadioGroup.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == intent.getStringExtra("bloodGroup")) {
                radioButton.isChecked = true
                break
            }
        }

        updateEmployee.setOnClickListener {
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

            val retrofitResponse = retrofitBuilder.updateUser(idSharedPre,
                Employee(_id = idSharedPre, firstName = firstName.text.toString(), lastName = lastName.text.toString(),
                emailId = emailId.text.toString(), imageUrl = imageURL.text.toString(), phoneNumber = phoneNumber.text.toString(),
                designation = selectedDesignationRadioBtnValue, department = selectedDepartmentRadioBtnValue, status=selectedStatusRadioBtnValue,
                position = selectedPositionRadioBtnValue, bloodGroup = selectedBloodGrpRadioBtnValue)
            )

            retrofitResponse.enqueue(object : Callback<Employee?> {
                override fun onResponse(call: Call<Employee?>, response: Response<Employee?>) {
                    Log.d("retrofit", "Update - Status Code: ${response.code()}, Body: ${response.body()}")
                    Toast.makeText(this@UpdateActivity, "Employee Successfully Updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Employee?>, t: Throwable) {
                    Log.d("retrofit", "${t.message}")
                    Toast.makeText(this@UpdateActivity, "Some error encountered", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        var intent = Intent(this@UpdateActivity, DetailsActivity::class.java)
//        startActivity(intent)
//    }
}