package ead.ca2.quotemagnet.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ead.ca2.quotemagnet.data.settings.SettingsPreferenceDataStore
import ead.ca2.quotemagnet.data.settings.SettingsRepository
import javax.inject.Singleton

const val PREFERENCE_FILE = "SettingsPreference"

@Module
@InstallIn(SingletonComponent::class)
class SettingsProviderModule {

    @Provides @Singleton
    fun provideSettingsDataStore(@ApplicationContext context:Context) : DataStore<Preferences>{

        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            migrations = listOf(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(PREFERENCE_FILE) }
        )
    }

    @Provides @Singleton
    fun provideSettingsPreferenceDataStore(settingsRepository: SettingsRepository) : PreferenceDataStore{

        return SettingsPreferenceDataStore(settingsRepository)
    }
}