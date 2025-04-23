package ead.ca2.quotemagnet.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsDataSource {


    private val userNames = stringPreferencesKey("usernamePref")
    private val langs = stringPreferencesKey("langPref")


    override fun getUserName(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[userNames].orEmpty()
        }

    override fun getLang(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[langs].orEmpty()
        }


    override suspend fun getUserNameSnapshot(): String? = dataStore.data.first()[userNames]
    override suspend fun getLangSnapshot(): String? = dataStore.data.first() [langs]



    override suspend fun setLang(lang: String) {
        dataStore.edit { preferences ->
            preferences[langs] = lang
        }
    }


    override suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[userNames] = userName
        }
    }





}