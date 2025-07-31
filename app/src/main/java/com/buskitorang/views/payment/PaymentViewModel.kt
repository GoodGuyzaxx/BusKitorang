package com.buskitorang.views.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.response.UserPaymentsResponse
import com.buskitorang.data.response.UserPaymentsResponseItem
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: BusRepository): ViewModel() {
    private val _paymentResponse = MutableLiveData<List<UserPaymentsResponseItem>>()
    val paymentResponse : LiveData<List<UserPaymentsResponseItem>> = _paymentResponse


    fun getUserPayment(id : Int) {
        viewModelScope.launch {
                val response = repository.getUserPayments(id)
                _paymentResponse.postValue(response)
//            try {
//            }catch (e : HttpException){
//                val errorString = e.response()?.errorBody().toString()
//                val errorBody = Gson().fromJson(errorString, DataPaymentItem::class.java)
//                _paymentResponse.postValue(errorBody)
//            }
        }
    }
}