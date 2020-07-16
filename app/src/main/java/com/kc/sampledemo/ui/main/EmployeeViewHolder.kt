package com.kc.sampledemo.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.kc.sampledemo.data.model.Employee
import com.kc.sampledemo.databinding.ItemEmployeeBinding

class EmployeeViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(employee: Employee, itemClickCallBack :  (data: Employee) -> Unit){
        binding.tvName.text = employee.employee_name
        binding.tvName.setOnClickListener {
            itemClickCallBack.invoke(employee)
        }
    }

}