package ead.ca2.quotemagnet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ead.ca2.quotemagnet.data.settings.SettingsDataSource
import ead.ca2.quotemagnet.data.settings.SettingsDataSourceImpl
import ead.ca2.quotemagnet.data.settings.SettingsRepository
import ead.ca2.quotemagnet.data.settings.SettingsRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {

    @Binds
    abstract fun bindSettingsDataSource(settingsDataSourceImpl: SettingsDataSourceImpl) : SettingsDataSource
    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl) : SettingsRepository
}