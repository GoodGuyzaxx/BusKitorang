package com.buskitorang.data.pref

import android.content.Context
import android.provider.ContactsContract
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SystemData")
class SystemPreferences @Inject constructor(private val dataStore : DataStore<Preferences>) {

    suspend fun saveAuthModel(auth : AuthModel){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = auth.token
            preferences[ID_KEY] = auth.id
            preferences[NAME_KEY] = auth.name
            preferences[PHONE_KEY] = auth.phone
            preferences[EMAIL_KEY] = auth.email
        }
    }

    fun getAuthData(): Flow<AuthModel>{
        return dataStore.data.map {pref ->
            AuthModel(
                pref[ID_KEY] ?: 0,
                pref[NAME_KEY] ?: "",
                pref[EMAIL_KEY] ?: "",
                pref[PHONE_KEY] ?: "",
                pref[TOKEN_KEY] ?: ""
            )
        }
    }

    suspend fun logout(){
        dataStore.edit { pref ->
            pref.clear()
        }
    }


    private companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ID_KEY = intPreferencesKey("id")
        private val NAME_KEY = stringPreferencesKey("name")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val EMAIL_KEY = stringPreferencesKey("email")
    }
}