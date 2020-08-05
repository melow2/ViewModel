package com.khs.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var testCount:Int = 0

    fun getCurrentCount(): Int {
        return testCount
    }

    fun getUpdateCount(): Int {
        return ++testCount
    }
}