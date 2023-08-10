package com.example.ems_crud.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ems_crud.DetailsActivity
import com.example.ems_crud.R
import com.example.ems_crud.model.Employee
import com.squareup.picasso.Picasso

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var employeeData: List<Employee> = ArrayList()

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        myListener = listener
    }

    class EmployeeViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        var fullName: TextView = itemView.findViewById(R.id.nameText)
        var profile_img : ImageView = itemView.findViewById(R.id.profile_image)
        var email : TextView = itemView.findViewById(R.id.emailText)
        var deptText : TextView = itemView.findViewById(R.id.deptText)
        var designationText : TextView = itemView.findViewById(R.id.designationText)
        init {
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(view, myListener)
    }

    override fun getItemCount(): Int {
        return employeeData.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        var employee: Employee = employeeData[position]
        holder.fullName.text = employee.firstName + " " + employee.lastName
        Picasso.get().load(employee.imageUrl).into(holder.profile_img)
        holder.email.text = employee.emailId
        holder.deptText.text = employee.department
        holder.designationText.text = employee.designation

//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
//            holder.itemView.context.startActivity(intent)
//        }

    }

    fun setAPIEmployeeData(employeeData: List<Employee>){
        this.employeeData = employeeData
        notifyDataSetChanged()
    }
}