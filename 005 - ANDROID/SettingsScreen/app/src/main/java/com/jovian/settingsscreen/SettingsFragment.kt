package com.jovian.settingsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment :PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.my_settings, rootKey)
        preferenceFragmentCompat = this
    }

    companion object {
        var preferenceFragmentCompat:PreferenceFragmentCompat?=null
    }
}