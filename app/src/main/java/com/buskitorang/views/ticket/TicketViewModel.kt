package com.buskitorang.views.ticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import com.buskitorang.data.response.UserTicketsResponse
import com.buskitorang.data.response.UserTicketsResponseItem
import com.buskitorang.repository.BusRepository
import com.google.gson.Gson
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException


@HiltViewModel
class TicketViewModel @Inject constructor(private val pref : SystemPreferences,private val repository: BusRepository): ViewModel() {

    private val _userTicketResponse = MutableLiveData<List<UserTicketsResponseItem>>()
    val userTicketResponse : LiveData<List<UserTicketsResponseItem>> = _userTicketResponse

    fun getAuth(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }

    fun getUserTickets(token : String){
        viewModelScope.launch {
            val response = repository.getUserTickets(token)
            _userTicketResponse.postValue(response)
//            try {
//
//            }catch (e : HttpException){
//                val stringError = e.response()?.body().toString()
//                val errorBody = Gson().fromJson(stringError, UserTicketsResponseItem::class.java)
//                _userTicketResponse.postValue(errorBody)
//            }
        }
    }

}