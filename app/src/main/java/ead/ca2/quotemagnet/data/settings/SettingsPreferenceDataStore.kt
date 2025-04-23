package ead.ca2.quotemagnet.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore @Inject constructor(
    private val settingsRepository: SettingsRepository
): PreferenceDataStore() {

    override fun putString(key: String?, value: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                "usernamePref" -> settingsRepository.setUserName(value ?: "")
                "langPref" -> settingsRepository.setLang(value ?: "")
                else -> {}
            }
        }


    }

    override fun getString(key: String?, defValue: String?) : String {
        var result: String?
        runBlocking(Dispatchers.IO) {
            result = when (key) {
                "usernamePref" -> settingsRepository.getUserNameSnapshot()
                "langPref" -> settingsRepository.getLangSnapshot()
                else -> null
            }
        }
        return result ?: defValue ?: ""
    }


}