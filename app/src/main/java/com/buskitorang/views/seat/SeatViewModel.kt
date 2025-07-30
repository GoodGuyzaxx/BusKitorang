package com.buskitorang.views.seat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.TicketsByRouteResponse
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SeatViewModel @Inject constructor(private val repository: BusRepository): ViewModel() {

    private val _ticketsResponse = MutableLiveData<TicketsByRouteResponse>()
    val ticketsResponse: LiveData<TicketsByRouteResponse> = _ticketsResponse


    fun getTicketsByRoute(id : Int){
        viewModelScope.launch {
            try {
                val response = repository.getTicketsByRoute(id)
                _ticketsResponse.postValue(response)
            }catch (e : HttpException){
                val errorString = e.response()?.body().toString()
                val errorBody = Gson().fromJson(errorString, TicketsByRouteResponse::class.java)
                _ticketsResponse.postValue(errorBody)
                Log.d(TAG, "getTicketsByRoute: ${errorBody}")
            }catch (e: Exception){

            }
        }
    }


    companion object {
        private val TAG = SeatViewModel::class.simpleName
    }
}