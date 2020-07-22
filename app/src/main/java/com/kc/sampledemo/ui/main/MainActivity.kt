package com.kc.sampledemo.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kc.sampledemo.R
import com.kc.sampledemo.data.model.Employee
import com.kc.sampledemo.databinding.ActivityMainBinding
import com.kc.sampledemo.di.kodeinViewModel
import com.kc.sampledemo.ui.details.DetailsActivity
import com.kc.sampledemo.viewmodel.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware {

    // import manually this package -> import org.kodein.di.android.kodein
    override val kodein: Kodein by kodein()

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by kodeinViewModel()

    private lateinit var employeeAdapter :EmployeeAdapter
    private var  employeeList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mMainViewModel = mainViewModel

        employeeAdapter = EmployeeAdapter(employeeList) {
            startActivity(Intent(this, DetailsActivity::class.java))
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
