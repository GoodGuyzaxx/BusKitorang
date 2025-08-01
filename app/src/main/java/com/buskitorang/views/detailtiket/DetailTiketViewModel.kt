package com.buskitorang.views.detailtiket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import com.buskitorang.data.response.UserPaymentResponseItem
import com.buskitorang.repository.BusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTiketViewModel @Inject constructor(private val repository: BusRepository, private val pref: SystemPreferences): ViewModel() {

    private val _paymentResponse = MutableLiveData<List<UserPaymentResponseItem>>()
    val paymentResponse : LiveData<List<UserPaymentResponseItem>> = _paymentResponse


    fun getAuthData(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }

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