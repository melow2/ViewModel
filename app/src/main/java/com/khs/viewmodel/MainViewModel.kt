package com.khs.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class MainViewModel(application: Application, startCount: Int) : AndroidViewModel(application) {
    private var testCount: Int = 0

    init {
        testCount = startCount
    }

    fun getCurrentCount(): Int {
        return testCount
    }

    fun getUpdateCount(): Int {
        return ++testCount
    }
}