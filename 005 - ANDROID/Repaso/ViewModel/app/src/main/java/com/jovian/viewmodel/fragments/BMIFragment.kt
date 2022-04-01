package com.jovian.viewmodel.fragments

import android.app.Activity
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.jovian.viewmodel.R
import com.jovian.viewmodel.databinding.FragmentBMIBinding
import com.jovian.viewmodel.model.BMICalculator
import com.jovian.viewmodel.viewModel.BMIViewModel
import java.text.DecimalFormat

class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBMIBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentBMIBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //obtenemos una referencia del viewModel
        val bmiViewModel: BMIViewModel = ViewModelProvider(this)[BMIViewModel::class.java]

        binding.btnCalcular.setOnClickListener {

            closeKeyBoard(it)

            //declaramos variables y le damos valor
            var peso = 0.0
            var altura = 0.0

            peso = binding.etPeso.text.toString().toDouble()
            altura = binding.etAltura.text.toString().toDouble()

            //llamamos a la funcion del viewModel
            bmiViewModel.calculate(peso, altura)

        }

        //observamos los cambios en el viewModel
        bmiViewModel.bmi.observe(viewLifecycleOwner){ newBMI ->
            val dec = DecimalFormat("#,###.00")

            binding.tvBMI.text = dec.format(newBMI).toString()

        }
    }

    private fun closeKeyBoard(view: View){
        (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}