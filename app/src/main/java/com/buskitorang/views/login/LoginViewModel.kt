package com.buskitorang.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import com.buskitorang.data.response.LoginPassenger
import com.buskitorang.data.response.LoginResponse
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: BusRepository, private val pref: SystemPreferences): ViewModel() {

    private val _responseLogin = MutableLiveData<LoginResponse>()
    val responseLogin : LiveData<LoginResponse> = _responseLogin

    fun getAuthData(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }

    fun postLogin(email: String, password: String){
        viewModelScope.launch {
            try {
                val response = repository.postLogin(email,password)
                _responseLogin.postValue(response)
                val authValue = AuthModel(
                    id = response.passenger.id,
                    name = response.passenger.name,
                    phone = response.passenger.phone,
                    email = response.passenger.email,
                    token = response.token
                )
                pref.saveAuthModel(authValue)
            }catch (e : HttpException){
                val errorBody = e.response()?.errorBody()?.toString()
                try {
                    if (errorBody != null){
                        val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                        _responseLogin.postValue(errorResponse)
                        Log.d("TAG", "postLogin: $errorResponse")
                    }
                }catch (json : JsonSyntaxException){
                    val fallBackError = LoginResponse(
                        message = json.message.toString(),
                        token = "",
                        passenger = LoginPassenger("","","","",0,"")
                    )
                    _responseLogin.postValue(fallBackError)
                    Log.d("TAG", "postLogin: $errorBody")
                }
            }catch (e : Exception){

            }
        }
    }
}