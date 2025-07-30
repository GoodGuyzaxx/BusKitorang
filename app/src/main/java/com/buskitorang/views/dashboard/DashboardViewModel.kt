package com.buskitorang.views.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.buskitorang.data.pref.AuthModel
import com.buskitorang.data.pref.SystemPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val pref: SystemPreferences): ViewModel(){

    fun getAuthData(): LiveData<AuthModel>{
        return pref.getAuthData().asLiveData()
    }
}