package com.buskitorang.views.detailbus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.DetailBusResponse
import com.buskitorang.repository.BusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class DetailBusViewModel @Inject constructor(private val repository: BusRepository): ViewModel() {

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
}