package ead.ca2.quotemagnet.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dagger.hilt.android.AndroidEntryPoint
import ead.ca2.quotemagnet.R
import ead.ca2.quotemagnet.data.settings.SettingsPreferenceDataStore
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {


    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        preferenceManager.preferenceDataStore = settingsPreferenceDataStore

        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

}