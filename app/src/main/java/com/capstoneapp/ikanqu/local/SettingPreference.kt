package com.capstoneapp.ikanqu.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreference private constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val userId = stringPreferencesKey("userId")

    fun getUserId(): Flow<String?> {
        return dataStore.data.map { pref ->
            pref[userId]
        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { pref ->
            pref[this.userId] = userId
        }
    }

    suspend fun clearUserLogin() {
        dataStore.edit { pref ->
            pref.remove(userId)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}