package com.buskitorang.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.RegisterResponse
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: BusRepository) : ViewModel() {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _errorResponseException = MutableLiveData<String>()
    val errorResponseException: LiveData<String> = _errorResponseException

    fun postRegister(name: String, email: String, phone: String, password: String){
        viewModelScope.launch {
            try {
                val response = repository.postRegister(name, email, phone, password)
                _registerResponse.postValue(response)
            } catch (e: HttpException) {
                try {
                    // Properly extract the error response body
                    val errorBody = e.response()?.errorBody()?.string()
                    if (errorBody != null) {
                        val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                        _registerResponse.postValue(errorResponse)
                        Log.d(TAG, "postRegister error: ${errorResponse.message}")
                    } else {
                        val fallbackError = RegisterResponse(
                            message = "HTTP ${e.code()}: ${e.message()}",
                        )
                        _registerResponse.postValue(fallbackError)
                        Log.d(TAG, "postRegister HTTP error: ${e.code()} - ${e.message()}")
                    }
                } catch (jsonException: Exception) {
                    // Handle JSON parsing errors
                    val fallbackError = RegisterResponse(
                        message = "Failed to parse error response: ${e.message()}",
                        // Add other required fields based on your RegisterResponse class
                    )
                    _registerResponse.postValue(fallbackError)
                    Log.e(TAG, "postRegister JSON parsing error: ${jsonException.message}")
                }
            } catch (e: Exception) {
                _errorResponseException.postValue(e.message.toString())
                Log.e(TAG, "postRegister general error: ${e.message}")
            }
        }
    }


    companion object {
        private val TAG = RegisterViewModel::class.simpleName
    }
}