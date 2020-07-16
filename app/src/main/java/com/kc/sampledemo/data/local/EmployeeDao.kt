package com.kc.sampledemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kc.sampledemo.data.model.Employee


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    suspend fun getAll(): MutableList<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<Employee>) : List<Long>
}