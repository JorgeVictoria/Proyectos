package com.jovian.fragmentexample.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jovian.fragmentexample.R
import com.jovian.fragmentexample.interfaces.OnFragmentActionsListener

class BlueFragment : Fragment() {

    private var listener: OnFragmentActionsListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPlus: Button = view.findViewById(R.id.btnPlus)

        btnPlus.setOnClickListener { listener?.onClickFragmentButton() }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



}