package com.jovian.settingsscreen

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.jovian.settingsscreen.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate (
            inflater,
            container,
            false
        ).also { binding=it}.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //obtaining sharedPreferences
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        listener=SharedPreferences.OnSharedPreferenceChangeListener() {prefs, key ->
            val preferences=prefs.all
            val s=preferences [key].toString()
            //write the changed shared into TextView
            binding.tvInfo.text=s

        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }
}