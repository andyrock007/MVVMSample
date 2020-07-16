package com.kc.sampledemo.ui.main


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kc.sampledemo.R
import com.kc.sampledemo.data.local.AppDatabase
import com.kc.sampledemo.data.model.Employee
import com.kc.sampledemo.data.network.HeaderInterceptor
import com.kc.sampledemo.data.network.WebApi
import com.kc.sampledemo.data.repo.DataRepository
import com.kc.sampledemo.databinding.ActivityMainBinding
import com.kc.sampledemo.ui.details.DetailsActivity
import com.kc.sampledemo.viewmodel.MainViewModel
import com.kc.sampledemo.viewmodel.factory.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private lateinit var employeeAdapter :EmployeeAdapter
    private var  employeeList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val headerInterceptor = HeaderInterceptor()
        val webApi = WebApi(headerInterceptor)
        val dataRepository = DataRepository(webApi)
        val db = AppDatabase.getDatabase(applicationContext)
        mainViewModel  = ViewModelProvider(this,ViewModelFactory(dataRepository,db)).get(MainViewModel::class.java)
        binding.mMainViewModel = mainViewModel

        employeeAdapter = EmployeeAdapter(employeeList) {
            //startActivity(Intent(this,DetailsActivity::class.java))
            Toast.makeText(this,it.employee_name, Toast.LENGTH_LONG).show()
        }

        binding.rvEmployee.adapter = employeeAdapter

        initObserver()

    }

    private fun initObserver() {
        mainViewModel.movieList.observe(this, Observer { list ->
            employeeList.clear()
            employeeList.addAll(list)
            employeeAdapter.notifyDataSetChanged()
        })
    }
}
