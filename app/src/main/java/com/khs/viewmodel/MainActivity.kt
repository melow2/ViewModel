package com.khs.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.khs.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinding:ActivityMainBinding
    lateinit var mainVM:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainVM = ViewModelProvider(this,MainViewModelFactory(application,100)).get(MainViewModel::class.java)
        mBinding.apply {
            tvCount.text = mainVM.getCurrentCount().toString()
            btnCount.setOnClickListener {
                tvCount.text = mainVM.getUpdateCount().toString()
            }
        }
    }
}