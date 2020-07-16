package com.kc.sampledemo.data.network

import com.kc.sampledemo.data.model.DummayModel
import com.kc.sampledemo.data.model.EmployeeResModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WebApi {

    companion object {
        operator fun invoke(
            headerInterceptor: HeaderInterceptor
        ): WebApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okkHttpclient = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebApi::class.java)
        }
    }

    @GET("employees")
    suspend fun getEmployees(): Response<EmployeeResModel>
}