package com.kc.sampledemo.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kc.sampledemo.data.local.AppDatabase
import com.kc.sampledemo.data.model.Employee
import com.kc.sampledemo.data.repo.DataRepository
import com.kc.sampledemo.utils.Coroutines

class MainViewModel( private val repository: DataRepository, private val appDatabase: AppDatabase) : ViewModel() {

    val movieList = MutableLiveData<MutableList<Employee>>()
    val errorText : ObservableField<String> = ObservableField<String>("")
    val progress : ObservableField<Boolean> = ObservableField<Boolean>(false)

    init {
        getLocalEmployees()
        getServerEmployees()
        Log.e("MainViewModel","Init")
    }

    fun getServerEmployees(){
        Coroutines.io {
            try {
                progress.set(true)
                val result = repository.getEmployees()
                result.apply {
                    when(status){
                        "success" -> {
                            appDatabase.employeeDao().insertAll(data)
                            getLocalEmployees()
                            progress.set(false)
                        }
                        else -> {
                            Log.e("MainViewModel", "Error")
                            progress.set(false)
                        }
                    }
                }
            } catch (e : Exception){
                Log.e("MainViewModel", "Exception:: ${e.message}")
                progress.set(false)
            }
        }
    }

    fun getLocalEmployees(){
        Coroutines.main {
            try {
                movieList.value =  appDatabase.employeeDao().getAll()
            } catch (e : Exception){
                Log.e("MainViewModel", "Exception:: ${e.message}")
                progress.set(false)
            }
        }
    }
}