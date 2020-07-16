package com.kc.sampledemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc.sampledemo.data.model.Employee
import com.kc.sampledemo.databinding.ItemEmployeeBinding

class EmployeeAdapter(private val employeeList: List<Employee>, private val itemClickCallBack :  (data: Employee) -> Unit) : RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater)
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position], itemClickCallBack)
    }
}