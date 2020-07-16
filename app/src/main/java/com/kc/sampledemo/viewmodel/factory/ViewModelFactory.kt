package com.kc.sampledemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kc.sampledemo.data.local.AppDatabase
import com.kc.sampledemo.data.repo.DataRepository
import com.kc.sampledemo.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: DataRepository, private val appDatabase: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(repository,appDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}