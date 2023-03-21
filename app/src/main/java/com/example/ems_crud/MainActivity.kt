package com.example.ems_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ems_crud.adapter.EmployeeAdapter
import com.example.ems_crud.model.Employee
import com.example.ems_crud.repository.EmployeeRepository
import com.example.ems_crud.retrofit.RetrofitBuilder
import com.example.ems_crud.services.RetrofitServices
import com.example.ems_crud.viewModel.EmployeeViewModel
import com.example.ems_crud.viewModel.EmployeeViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var createButton: Button
    lateinit var employeeViewModel: EmployeeViewModel
    lateinit var employeeData: List<Employee>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Dashboard"

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var employeeAdapter = EmployeeAdapter()
        recyclerView.adapter = employeeAdapter

        val retrofitServices = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)
        val repository = EmployeeRepository(retrofitServices)
        employeeViewModel = ViewModelProvider(this, EmployeeViewModelFactory(repository)).get(EmployeeViewModel::class.java)

        employeeViewModel.usersLiveData.observe(this, Observer {employees ->
            employeeAdapter.setAPIEmployeeData(employees)
            employeeData = employees
        })

        employeeAdapter.setOnItemClickListener(object : EmployeeAdapter.onItemClickListener {
            override fun onItemClicking(position: Int) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("_id", employeeData[position]._id)
                intent.putExtra("firstName", employeeData[position].firstName)
                intent.putExtra("lastName", employeeData[position].lastName)
                intent.putExtra("imageUrl", employeeData[position].imageUrl)
                intent.putExtra("emailId", employeeData[position].emailId)
                intent.putExtra("phoneNumber", employeeData[position].phoneNumber)
                intent.putExtra("designation", employeeData[position].designation)
                intent.putExtra("department", employeeData[position].department)
                intent.putExtra("status", employeeData[position].status)
                intent.putExtra("position", employeeData[position].position)
                intent.putExtra("bloodGroup", employeeData[position].bloodGroup)
                startActivity(intent)
//                finish()
            }
        })
//
//        var employeeRepo = RetrofitBuilder.getInstance().create(RetrofitServices::class.java)
//        var employeeData = employeeRepo.getAllUsers()
//
//        employeeData.enqueue(object : Callback<List<Employee>?> {
//            override fun onResponse(call: Call<List<Employee>?>, response: Response<List<Employee>?>) {
//                var responseBody = response.body()
//                Log.d("retrofit", "$responseBody")
//                if (responseBody != null) {
//                    recyclerView.adapter = EmployeeAdapter(responseBody)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Employee>?>, t: Throwable) {
//                Log.d("retrofit", "Failed ${t.message}")
//            }
//        })


        createButton = findViewById(R.id.createEmp)
        createButton.setOnClickListener{
            intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}