package com.kc.sampledemo.ui.details

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
import com.kc.sampledemo.databinding.ActivityDetailsBinding
import com.kc.sampledemo.databinding.ActivityMainBinding
import com.kc.sampledemo.di.kodeinViewModel
import com.kc.sampledemo.ui.main.EmployeeAdapter
import com.kc.sampledemo.viewmodel.MainViewModel
import com.kc.sampledemo.viewmodel.factory.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class DetailsActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val TAG = DetailsActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailsBinding

    private val mainViewModel: MainViewModel by kodeinViewModel()

    private lateinit var employeeAdapter : EmployeeAdapter
    private var  employeeList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        //val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val headerInterceptor = HeaderInterceptor()
//        val webApi = WebApi(headerInterceptor)
//        val dataRepository = DataRepository(webApi)
//        val db = AppDatabase.getDatabase(applicationContext)
//        mainViewModel  = ViewModelProvider(this, ViewModelFactory(dataRepository,db)).get(MainViewModel::class.java)
        binding.mMainViewModel = mainViewModel

        employeeAdapter = EmployeeAdapter(employeeList) {
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
