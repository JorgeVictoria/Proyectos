package com.jovian.settingcolors

import android.content.SharedPreferences
import android.graphics.Color.parseColor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.preference.PreferenceManager
import com.jovian.settingcolors.databinding.FragmentMainBinding

const val BACKGROUND_KEY ="background_color"
const val FONT_KEY ="font_color"
const val TITLE_KEY ="title_preference"

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(
            inflater,
            container,
            false
        ).also { binding=it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*Obtaining SharedPreferences*/
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext() /* Activity context */)

        listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
            val preferences = prefs.all
            when(key){
                BACKGROUND_KEY  -> changeBackgroundColor(preferences[key].toString())
                FONT_KEY        -> changeFontColor(preferences[key].toString())
                TITLE_KEY       -> changeTitle(preferences[key].toString())
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        //changeBackgroundColor(sharedPreferences.getString(BACKGROUND_KEY,"#FFFFFFFF")!!)
        //changeFontColor(sharedPreferences.getString(FONT_KEY,"#000000")!!)
        changeTitle(sharedPreferences.getString(TITLE_KEY,"My App")!!)


    }

    private fun changeTitle(title:String) {
        requireActivity().title = title

    }

    private fun changeFontColor(color:String) {
        for(v:View in binding.root.children){
            if(v is TextView){
                v.setTextColor(parseColor(color))
            }
        }
    }

    private fun changeBackgroundColor(color:String) {
        Log.i("Uncolor", color)
        binding.root.setBackgroundColor(parseColor(color))


    }
}