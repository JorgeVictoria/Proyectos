package com.jovian.settingcolors

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment :  PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.my_settings, rootKey)
        preferenceFragmentCompat = this
    }

    companion object {
        var preferenceFragmentCompat: PreferenceFragmentCompat? = null
    }


}