package ead.ca2.quotemagnet.data.settings


import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getUserName() : Flow<String>


    fun getLang() : Flow<String>

    suspend fun getUserNameSnapshot() : String?

    suspend fun getLangSnapshot() : String?

    suspend fun setLang(lang:String)

    suspend fun setUserName(userName : String)


}