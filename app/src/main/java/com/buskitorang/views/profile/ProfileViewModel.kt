package com.buskitorang.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import com.buskitorang.repository.BusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val pref: SystemPreferences, private val repository: BusRepository): ViewModel() {

    fun getAuthData(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }

    fun postLogout(token:String){
        viewModelScope.launch {
            repository.postLogout(token)
        }
    }

    fun getLogout(){
        viewModelScope.launch {
            pref.logout()
        }
    }


}