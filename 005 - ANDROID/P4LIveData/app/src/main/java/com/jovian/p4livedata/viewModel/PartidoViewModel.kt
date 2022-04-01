package com.jovian.p4livedata.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jovian.p4livedata.model.Partido
import com.jovian.p4livedata.R


class PartidoViewModel(application: Application) : AndroidViewModel(application) {

    //nos creamos un objeto de tipo Partido
    private var match: Partido = Partido()

    //variable livedata para controlar los eventos del partido
    lateinit var matchLiveData : LiveData<Int>

    //variable livedata para controlar el tiempo del partido
    lateinit var tiempoLiveData : LiveData<String>

    //variable para los goles locales
    lateinit var golesLocalesLiveData: LiveData<String>

    //variable para los goles visitantes
    lateinit var golesVistantesLiveData: LiveData<String>

    //variables para las cuotas del equipo local
    lateinit var cuotaLocalLiveData: LiveData<String>

    //variables para las cuotas del equipo local
    lateinit var cuotaEmpateLiveData: LiveData<String>

    //variables para las cuotas del equipo visitante
    lateinit var cuotaVisitanteLiveData: LiveData<String>

    //controlamos los cambios en los datos del partido
    init {

        matchLiveData = Transformations.switchMap(match.matchLiveData,){
                eventoPartido ->
            val mEventoPartido = eventoPartido.split(":")[0]
            if(mEventoPartido != null){

                var imageID:Int = when(mEventoPartido){
                    "INICIOPARTIDO" -> R.drawable.inicio
                    "GOLLOCAL" -> R.drawable.gollocal
                    "PELIGROLOCAL" -> R.drawable.peligrolocal
                    "POSESIONLOCAL" -> R.drawable.posesionlocal
                    "POSESIONVISITANTE" -> R.drawable.posesionvisitante
                    "PELIGROVISITANTE" -> R.drawable.peligrovisitante
                    "FINALPARTIDO" -> R.drawable.finalpartido
                    "DESCANSO" -> R.drawable.descanso
                    else -> R.drawable.golvisitante
                }
                return@switchMap MutableLiveData<Int>(imageID)
            }

            return@switchMap null
        }

        tiempoLiveData = Transformations.switchMap(match.matchLiveData) { tiempoPartido ->
            return@switchMap MutableLiveData<String>(tiempoPartido.split(":")[1])
        }

        golesLocalesLiveData = Transformations.switchMap(match.matchLiveData) { golesLocales ->
            return@switchMap MutableLiveData<String>(golesLocales.split(":")[2])
        }

        golesVistantesLiveData = Transformations.switchMap(match.matchLiveData) { golesVisitantes ->
            return@switchMap MutableLiveData<String>(golesVisitantes.split(":")[3])
        }

        cuotaLocalLiveData = Transformations.switchMap(match.matchLiveData) { cuotaLocal ->
            return@switchMap MutableLiveData<String>(cuotaLocal.split(":")[4])
        }

        cuotaVisitanteLiveData = Transformations.switchMap(match.matchLiveData) { cuotaVisitante ->
            return@switchMap MutableLiveData<String>(cuotaVisitante.split(":")[6])
        }

        cuotaEmpateLiveData = Transformations.switchMap(match.matchLiveData) { cuotaEmpate ->
            return@switchMap MutableLiveData<String>(cuotaEmpate.split(":")[5])
        }
    }
}
