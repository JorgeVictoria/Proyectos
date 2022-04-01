package com.jovian.livedatatrainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jovian.livedatatrainer.databinding.FragmentTrainerBinding
import com.jovian.livedatatrainer.viewModel.TrainerViewModel


class TrainerFragment : Fragment() {

    //variable para recoger todos los elementos de la vista
    private lateinit var binding:FragmentTrainerBinding

    //cogemos todos los elementos de la vista. En principio fijo para todos los fragmentos
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTrainerBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    //aqui empezamos a recoger info de la app y darle funcionalidad
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainerViewModel = ViewModelProvider(this)[TrainerViewModel::class.java]

        trainerViewModel.exerciseLiveData.observe(viewLifecycleOwner){ imageID ->
            binding.ivExercise.setImageResource(imageID)
        }

        trainerViewModel.repetitionLiveData.observe(viewLifecycleOwner) { repetition ->
            if (repetition.equals("CHANGE")) {
                binding.tvChange.visibility = View.VISIBLE
            } else {
                binding.tvChange.visibility = View.GONE
            }
            binding.tvRepetition.text = repetition
        }
    }
}