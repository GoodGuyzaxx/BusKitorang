package com.buskitorang.views.rute

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.RouteOriginResponse
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RuteViewModel @Inject constructor(private val repository: BusRepository): ViewModel() {

    private val _routeResponse = MutableLiveData<RouteOriginResponse>()
    val routeResponse: LiveData<RouteOriginResponse> = _routeResponse


    fun getRouteWithOrigin(route: String){
        viewModelScope.launch {
            try {
                val response = repository.getRouteWithOrigin(route)
                _routeResponse.postValue(response)
            }catch (e: HttpException){
                val errorString  = e.response()?.body().toString()
                val errorBody = Gson().fromJson(errorString, RouteOriginResponse::class.java)
                _routeResponse.postValue(errorBody)
            }catch (e : Exception){

            }
        }
    }

}