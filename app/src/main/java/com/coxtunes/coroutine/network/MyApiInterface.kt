package com.coxtunes.coroutine.network

import com.coxtunes.coroutine.model.EmployeeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface MyApiInterface {

    /*
    * Here we use suspende & call change to Response
    * */

    @POST("test.php")
    suspend fun getEmployeeList() : Response<EmployeeResponse>

//    @POST("test.php")
//    fun getEmployeeList() : Call<EmployeeResponse>

    companion object
    {
        operator fun invoke(): MyApiInterface {

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.coxtunes.com/project/cbiusmartapp/")
                .build()
                .create(MyApiInterface::class.java)
        }
    }
}