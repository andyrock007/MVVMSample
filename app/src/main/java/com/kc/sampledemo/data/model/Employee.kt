package com.kc.sampledemo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Employee (
        @PrimaryKey
        var id: String,
        @ColumnInfo(name = "employee_name") var employee_name: String? = null,
        @ColumnInfo(name = "employee_salary") var employee_salary:String? = null,
        @ColumnInfo(name = "employee_age") var employee_age: String? = null,
        @ColumnInfo(name = "profile_image") var profile_image: String? = null
) : Serializable
