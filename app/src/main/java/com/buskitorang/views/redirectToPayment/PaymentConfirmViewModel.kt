package com.buskitorang.views.redirectToPayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import com.buskitorang.data.response.PaymentResponse
import com.buskitorang.repository.BusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentConfirmViewModel @Inject constructor(private val pref: SystemPreferences,private val repository: BusRepository): ViewModel() {

    private val _paymentResponse = MutableLiveData<PaymentResponse>()
    val paymentResponse : LiveData<PaymentResponse> = _paymentResponse

    fun postPayment(token: String, routeId: Int, seatNumber: Int){
        viewModelScope.launch {
            val response = repository.postPayment(token, routeId, seatNumber)
            _paymentResponse.postValue(response)
        }
    }

    fun getAuth(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }

}