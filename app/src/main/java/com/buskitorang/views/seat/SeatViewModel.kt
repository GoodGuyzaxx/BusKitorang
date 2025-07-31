package com.buskitorang.views.seat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.DetailBusResponse
import com.buskitorang.data.response.TicketsByRouteResponse
import com.buskitorang.data.response.TicketsByRouteResponseItem
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SeatViewModel @Inject constructor(private val repository: BusRepository): ViewModel() {

    private val _ticketsResponse = MutableLiveData<List<TicketsByRouteResponseItem>>()
    val ticketsResponse: LiveData<List<TicketsByRouteResponseItem>> = _ticketsResponse

    private val _occupiedSeats = MutableLiveData<List<Int>>()
    val occupiedSeats: LiveData<List<Int>> = _occupiedSeats

    fun setOccupiedSeatsFromApi(tickets: List<TicketsByRouteResponseItem>) {
        val occupiedSeats = tickets
            .filterNotNull()              // Hilangkan item yang null
            .filter { it.seatNumber > 0 }  // Hanya seat_number yang valid
            .map { it.seatNumber }
        _occupiedSeats.value = occupiedSeats
    }



    fun getTicketsByRoute(id : Int){
        viewModelScope.launch {
            try {
                val response = repository.getTicketsByRoute(id)
                _ticketsResponse.postValue(response)
            }catch (e : HttpException){
                val errorString = e.response()?.body().toString()
                val errorBody = Gson().fromJson(errorString, TicketsByRouteResponseItem::class.java)
                _ticketsResponse.postValue(listOf(errorBody))
                Log.d(TAG, "getTicketsByRoute: ${errorBody}")
            }
        }
    }

    private val _busResponse = MutableLiveData<DetailBusResponse>()
    val busResponse: LiveData<DetailBusResponse> = _busResponse


    fun getBusDetail(id : Int){
        viewModelScope.launch {
            try {
                val response = repository.getBusDetail(id)
                _busResponse.postValue(response)
            }catch (e: HttpException){

            }catch (e: Exception){

            }
        }
    }


    companion object {
        private val TAG = SeatViewModel::class.simpleName
    }
}