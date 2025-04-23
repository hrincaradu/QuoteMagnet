package ead.ca2.quotemagnet.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(

    private val settingsDataSource: SettingsDataSource

) : SettingsRepository {
    override fun getUserName(): Flow<String> = settingsDataSource.getUserName()
    override fun getLang(): Flow<String> = settingsDataSource.getLang()



    override suspend fun getUserNameSnapshot(): String? = settingsDataSource.getUserNameSnapshot()
    override suspend fun getLangSnapshot(): String? = settingsDataSource.getLangSnapshot()

    override suspend fun setLang(lang: String) {
        settingsDataSource.setLang(lang)
    }


    override suspend fun setUserName(userName : String) {
        settingsDataSource.setUserName(userName)
    }
}