package com.kc.sampledemo.data.model

import com.google.gson.annotations.SerializedName

data class EmployeeResModel(
    @SerializedName("data")
    val `data`: List<Employee>,
    @SerializedName("status")
    val status: String)