package com.android.taskfriday

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.taskfriday.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel : ViewModel() {
    
    private val fetchedFields = MutableLiveData<MutableList<MutableList<User>>>().apply{
        MutableLiveData<MutableList<MutableList<User>>>()
    }
    
    val _fetchedFields = fetchedFields
    
    fun init(){
        viewModelScope.launch{
            withContext(Dispatchers.Default){
                populate()
            }
        }
    }

    private suspend fun populate() {
        val response = RetrofitService.retrofit().getRequest()
        d("isSuccesfful","${response.isSuccessful}")
        fetchedFields.postValue(response.body() as MutableList<MutableList<User>>?)
    }
}