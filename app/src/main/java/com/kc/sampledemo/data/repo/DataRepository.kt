package com.kc.sampledemo.data.repo

import com.kc.sampledemo.data.network.SafeApiRequest
import com.kc.sampledemo.data.network.WebApi

class DataRepository (private val api: WebApi) : SafeApiRequest(){

    suspend fun getEmployees() = apiRequest {
        api.getEmployees()
    }
}