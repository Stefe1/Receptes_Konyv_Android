package hu.bme.aut.android.receptes_konyv.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Settings(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")
        private val THEME = stringPreferencesKey("theme")
        private val SORT = stringPreferencesKey("sort")
    }

    val getAppTheme: Flow<String> = context.dataStore.data.map {
            preferences ->
        preferences[THEME] ?: "light"
    }


    suspend fun saveTheme(theme: String){
        if(theme=="light"){
            context.dataStore.edit { preferences->
                preferences[THEME]="dark"
            }
        }
        else{
            context.dataStore.edit { preferences->
                preferences[THEME]="light"
            }
        }
    }
    val getSorting: Flow<String> = context.dataStore.data.map {
            preferences ->
        preferences[SORT] ?: "none"
    }

    suspend fun saveSorting(sorting: String){
        if(sorting=="title"){
            context.dataStore.edit { preferences->
                preferences[SORT]="title"
            }
        }
        else if(sorting=="date"){
            context.dataStore.edit { preferences->
                preferences[SORT]="date"
            }
        }
        else{
            context.dataStore.edit { preferences->
                preferences[SORT]="none"
            }
        }
    }

}