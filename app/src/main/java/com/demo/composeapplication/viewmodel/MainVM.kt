package com.demo.composeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM : ViewModel() {
    private var _countLD = MutableLiveData<Int>(0)
    var countLD : LiveData<Int> = _countLD

    fun changeCount(isAdd: Boolean) {
        _countLD.value?.let {
            _countLD.value = if(isAdd) it + 1 else it - 1
        }
    }
}