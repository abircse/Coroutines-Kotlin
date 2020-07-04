package com.coxtunes.coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coxtunes.coroutine.R
import com.coxtunes.coroutine.model.EmployeeData
import com.coxtunes.coroutine.model.EmployeeResponse
import com.coxtunes.coroutine.network.MyApiInterface
import com.example.PixelLime.adapter.EmployeeAdapter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /*
    * Note About Dispatchers
    *
    * Dispatchers.IO -> network request or disk read write
    * Dispatchers.Main -> Main thread Safety
    * Dispatchers.Default -> CPU Intensive Operation
    * */

    lateinit var recyclerView: RecyclerView
    lateinit var employeeAdapter: EmployeeAdapter
    lateinit var employeeList: List<EmployeeData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        employeeList = ArrayList()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        launch {
            getEmployee()
        }
    }

    private suspend fun getEmployee()
    {
        val response = MyApiInterface().getEmployeeList()
        employeeList = response.body()!!.data
        employeeAdapter = EmployeeAdapter(employeeList,this@MainActivity)
        recyclerView.adapter = employeeAdapter
        employeeAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

//    private fun getEmployeeList() {
//         MyApiInterface.invoke().getEmployeeList().enqueue(object : Callback<EmployeeResponse>{
//            override fun onFailure(call: Call<EmployeeResponse>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//            override fun onResponse(call: Call<EmployeeResponse>, response: Response<EmployeeResponse>)
//            {
//                    employeeList = response.body()!!.data
//                    employeeAdapter = EmployeeAdapter(employeeList,this@MainActivity)
//                    recyclerView.adapter = employeeAdapter
//                    employeeAdapter.notifyDataSetChanged()
//            }
//        })
//    }
}