package com.jovian.bodymass

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jovian.bodymass.databinding.FragmentBMIBinding
import com.jovian.bodymass.viewModel.BMICalculatorViewModel
import java.lang.Exception
import java.text.DecimalFormat

class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBMIBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentBMIBinding
            .inflate(inflater, container, false)
            .also { binding = it }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //obtenemos la referencia del viewModel
        val bmiCalculatorViewModel: BMICalculatorViewModel = ViewModelProvider(this)[BMICalculatorViewModel::class.java]

        binding.btnCalculate.setOnClickListener {

            //al pulsar el boton cerramos el teclado. Llamada al metodo
            closeKeyBoard(it)

            //creamos las variables para el peso y altura. Las inicializamos a 0. Tambien una variable para ver errores
            var error = false
            var mHeight = 0.0
            var mWeight = 0.0

            //cogemos los datos introducidos por pantalla, vemos que no esten vacios
            binding.etHeight.error = null
            binding.etWeight.error = null

            //controlamos que se haya introducido la altura
            try {
                mHeight = binding.etHeight.text.toString().toDouble()
            } catch (e : Exception){
                binding.etHeight.error = "Debe insertar un numero"
                error = true
            }

            //controlamos que se haya introducido el peso
            try {
                mWeight = binding.etWeight.text.toString().toDouble()
            } catch (e : Exception){
                binding.etWeight.error = "Debe insertar un numero"
                error = true
            }

            //si no hay error, llamamos a la funcion calculate del viewModel
            bmiCalculatorViewModel.calculate(mWeight, mHeight)
        }

        //observamos los cambios en la variable bmi del viewModel
        bmiCalculatorViewModel.bmi.observe(viewLifecycleOwner){ newBMI ->

            //creamos una variable para crear un patron
            val dec = DecimalFormat("#,###.00")

            //imprimimos por pantalla el calculo
            binding.tvBMI.text = dec.format(newBMI).toString()

            //ponemos a null los errores
            binding.etWeight.error = null
            binding.etHeight.error = null
        }

        //observamos los cambios en la variable weighterror
        bmiCalculatorViewModel.weightError.observe(viewLifecycleOwner){ error ->
            if(error != "") {
                binding.tvBMI.text = ""
                binding.etWeight.error = error
            } else
                binding.etWeight.error = null
        }

        //observamos los cambios en la variable heighterror
        bmiCalculatorViewModel.heightError.observe(viewLifecycleOwner){ error ->
            if(error != "") {
                binding.tvBMI.text = ""
                binding.etHeight.error = error
            } else
                binding.etHeight.error = null
        }

        //observamos los cambios en la variable error
        bmiCalculatorViewModel.error.observe(viewLifecycleOwner){ error ->
            if(error != "") binding.tvBMI.text = error
        }

        //observamos los cambios en la variable loading
        bmiCalculatorViewModel.loading.observe(viewLifecycleOwner){isLoading ->
            if(isLoading) binding.progressCircular.visibility = View.VISIBLE
            else binding.progressCircular.visibility = View.GONE
        }

    }

    private fun closeKeyBoard(view: View){
        (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
