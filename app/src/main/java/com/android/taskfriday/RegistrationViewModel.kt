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

    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                populate()
            }
        }
    }

    private suspend fun populate() {
        val response = RetrofitService.retrofit().getRequest()
        fetchedFields.postValue(response.body() as MutableList<MutableList<InputFieldModel>>?)
    }

    fun isRequired(id: Int): Boolean? {
        fetchedFields.value?.forEach { it ->
            it.forEach {
                if (it.field_id == id) {
                    return it.required
                }
            }
        }
        return null;
    }


}