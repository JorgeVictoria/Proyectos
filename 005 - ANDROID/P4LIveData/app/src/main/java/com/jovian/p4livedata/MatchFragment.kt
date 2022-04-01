package com.jovian.p4livedata

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jovian.p4livedata.databinding.FragmentMatchBinding
import com.jovian.p4livedata.viewModel.PartidoViewModel


class MatchFragment : Fragment() {

    //variable para almacenar los elementos
    private lateinit var binding: FragmentMatchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        return FragmentMatchBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    @SuppressLint("SetTextI18n")        //cuando intento concatenar texto, el IDE me sugiere esto
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //enlazamos con el viewModel e imprimimos por pantalla los datos recibidos
        val partidoViewModel = ViewModelProvider (this)[PartidoViewModel::class.java]

        partidoViewModel.matchLiveData.observe(viewLifecycleOwner){ imageID ->
            binding.ivcampoFutbol.setImageResource(imageID)
        }

        partidoViewModel.tiempoLiveData.observe(viewLifecycleOwner) { tiempo ->
            binding.tvTiempo.text = "min " + tiempo
        }

        partidoViewModel.golesVistantesLiveData.observe(viewLifecycleOwner) { golesVisitantes ->
            binding.tvMarcadorVisitante.text = golesVisitantes
        }

        partidoViewModel.golesLocalesLiveData.observe(viewLifecycleOwner) { golesLocales ->
            binding.tvMarcadorLocal.text = golesLocales
        }

        partidoViewModel.cuotaLocalLiveData.observe(viewLifecycleOwner) { cuotaLocal ->
            binding.tvCuotaLocal.text = cuotaLocal
        }

        partidoViewModel.cuotaEmpateLiveData.observe(viewLifecycleOwner) { cuotaEmpate->
            binding.tvCuotaEmpate.text = cuotaEmpate
        }

        partidoViewModel.cuotaVisitanteLiveData.observe(viewLifecycleOwner) { cuotaVisitante ->
            binding.tvCuotaVisitante.text = cuotaVisitante
        }
    }




}



