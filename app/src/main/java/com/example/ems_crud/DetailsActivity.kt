package com.example.ems_crud

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ems_crud.model.Employee
import com.example.ems_crud.retrofit.RetrofitBuilder
import com.example.ems_crud.services.RetrofitServices
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar!!.title = "${intent.getStringExtra("firstName")} ${intent.getStringExtra("lastName")}"

        val fullName = findViewById<TextView>(R.id.detailsFullName)
        val email = findViewById<TextView>(R.id.detailsEmail)
        val imageUrl = findViewById<ImageView>(R.id.detailsProfile_image)
        val designation = findViewById<TextView>(R.id.detailsDesignationText)
        val department = findViewById<TextView>(R.id.detailsDept)
        val status = findViewById<TextView>(R.id.detailsStatus)
        val position = findViewById<TextView>(R.id.detailsPosition)
        val bloodGroup = findViewById<TextView>(R.id.detailsBloodGroup)
        val phoneNumber = findViewById<TextView>(R.id.detailsPhoneNumber)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val imageUrlURL = intent.getStringExtra("imageUrl")
        val _id = intent.getIntExtra("_id", 0)
        fullName.setText("$firstName $lastName")
        Picasso.get().load(imageUrlURL).into(imageUrl)
        designation.setText(intent.getStringExtra("designation"))
        bloodGroup.setText(intent.getStringExtra("bloodGroup"))
        email.setText(intent.getStringExtra("emailId"))
        department.setText(intent.getStringExtra("department"))
        status.setText(intent.getStringExtra("status"))
        position.setText(intent.getStringExtra("position"))
        phoneNumber.setText(intent.getStringExtra("phoneNumber"))

//        Using shared preferences
        var sp = this.getSharedPreferences("EMS_CRUD", MODE_PRIVATE)
        var editor = sp.edit()
        editor.putInt("update_id", _id)
        editor.apply()


        val deleteBtn = findViewById<ImageButton>(R.id.deleteBtn)
        val updateBtn = findViewById<ImageButton>(R.id.updateBtn)
        val retrofitBuilder = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)
        val alertDialog = AlertDialog.Builder(this@DetailsActivity)
        deleteBtn.setOnClickListener {
            alertDialog.setTitle("Delete Employee")
                .setMessage("Are you sure you want to delete ${fullName.text}")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val retrofitresponse = retrofitBuilder.deleteUser(_id)
                    retrofitresponse.enqueue(object : Callback<Employee?> {
                        override fun onResponse(call: Call<Employee?>, response: Response<Employee?>) {
                            Log.d("retrofit", "Delete - Status Code: ${response.code()}, Body: ${response.body()}")
                            Toast.makeText(this@DetailsActivity, "User Deleted", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                        override fun onFailure(call: Call<Employee?>, t: Throwable) {
                            Log.d("retrofit", "${t.message}")
                            Toast.makeText(this@DetailsActivity, "Some error encountered", Toast.LENGTH_SHORT).show()
                        }
                    })
                }).setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@DetailsActivity, "No Clicked", Toast.LENGTH_SHORT).show()
                })
            alertDialog.create().show()
        }

        updateBtn.setOnClickListener {
            intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("_id", _id)
            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            intent.putExtra("imageUrl", imageUrlURL)
            intent.putExtra("emailId", email.text.toString())
            intent.putExtra("phoneNumber", phoneNumber.text.toString())
            intent.putExtra("designation", designation.text.toString())
            intent.putExtra("department", department.text.toString())
            intent.putExtra("status", status.text.toString())
            intent.putExtra("position", position.text.toString())
            intent.putExtra("bloodGroup", bloodGroup.text.toString())
            startActivity(intent)
        }

//        var updateTrialBtn = findViewById<Button>(R.id.updateTrialBtn)
//        val retrofitBuilder = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)
//        updateTrialBtn.setOnClickListener {
//            val retrofitresponse = retrofitBuilder.updateUser(52000030, Employee(_id=52000030, firstName = "Harshbardhan",
//            lastName = "Singh", emailId = "monkmin8@gmail.com", imageUrl = "https://harsh1x4.github.io", designation = "Designer",
//            department = "IT", position = "Senior", status = "Onboarding", bloodGroup = "O-", phoneNumber = "+91 9123456789"))
//
//            retrofitresponse.enqueue(object : Callback<Employee?> {
//                override fun onResponse(call: Call<Employee?>, response: Response<Employee?>) {
//                    Log.d("retrofit", "Status Code: ${response.code()}, Body: ${response.body()}")
//                }
//
//                override fun onFailure(call: Call<Employee?>, t: Throwable) {
//                    Log.d("retrofit", "${t.message}")
//                }
//            })
//        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        var intent = Intent(this@DetailsActivity, MainActivity::class.java)
//        startActivity(intent)
//    }

    override fun onResume() {
        super.onResume()
        val _id = intent.getIntExtra("_id", 0)
        Log.d("onResume", "Details onResume $_id")
    }
}