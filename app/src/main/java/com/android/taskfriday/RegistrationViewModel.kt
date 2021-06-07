package com.android.taskfriday

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.taskfriday.api.RetrofitService
import com.android.taskfriday.model.InputFieldModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel : ViewModel() {

    private val fetchedFields = MutableLiveData<MutableList<MutableList<InputFieldModel>>>().apply {
        MutableLiveData<MutableList<MutableList<InputFieldModel>>>()
    }

    val _fetchedFields = fetchedFields


    private val mapOfRequiredFields = MutableLiveData<MutableMap<Int, InputFieldModel>>().apply {
        MutableLiveData<MutableMap<Int, InputFieldModel>>()
    }

    val _mapOfRequiredFields = mapOfRequiredFields

    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                populate()
            }
        }
    }

    private suspend fun populate() {
        val response = RetrofitService.retrofit().getRequest()
        val data = response.body() as MutableList<MutableList<InputFieldModel>>?
        fetchedFields.postValue(data)
        val dataMap = mutableMapOf<Int, InputFieldModel>()
        data!!.forEach { it ->
            it.forEach {
                dataMap[it.field_id] = it
            }
        }
        mapOfRequiredFields.postValue(dataMap)
    }
}