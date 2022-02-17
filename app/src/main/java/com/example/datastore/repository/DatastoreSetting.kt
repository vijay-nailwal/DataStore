package com.example.datastore.repository


/**
 * Created by Vijay on 17-02-2022.
 */

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreSetting
@Inject
constructor(@ApplicationContext private val context: Context) {

    object PreferencesKey {
        val name = stringPreferencesKey("name")
    }

    private val Context.dataStore by preferencesDataStore(name = "setting")

    suspend fun saveToLocal(name: String) {
        context.dataStore.edit { preference->
            preference[PreferencesKey.name] = name
        }
    }

    val readFromLocal : Flow<String> = context.dataStore.data
        .catch {
            if(this is Exception){
                emit(emptyPreferences())
            }
        }.map { preference->
            val name = preference[PreferencesKey.name] ?: ""
            name
        }
}