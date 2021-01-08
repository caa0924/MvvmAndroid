package com.ning.mvvmplayandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fule.com.playandroidkotlin.base.BaseModel
import kotlinx.coroutines.launch

/**
 *@anthor:njb
 *@date: 2020-05-09 03:57
 *@desc:
 **/
abstract class BaseViewModel <T> : ViewModel() {
    abstract fun loadData()

    private val _successData = MutableLiveData<T>()
    private val _errorData = MutableLiveData<BaseModel<Any?>>()

    val successData: LiveData<T>
        get() = _successData

    val errorData: LiveData<BaseModel<Any?>>
        get() = _errorData

    protected fun handleSuccess(data: T) {
        _successData.value = data
    }

    protected fun handleFailure(reason: BaseModel<Any?>) {
        _errorData.value = reason
    }

    fun refresh() {
        viewModelScope.launch { loadData() }
    }

    fun retry() {
        viewModelScope.launch { loadData() }
    }
}