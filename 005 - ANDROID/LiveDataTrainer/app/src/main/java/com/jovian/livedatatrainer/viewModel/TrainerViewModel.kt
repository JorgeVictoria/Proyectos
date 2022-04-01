package com.jovian.livedatatrainer.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jovian.livedatatrainer.R
import com.jovian.livedatatrainer.model.Trainer

class TrainerViewModel(application: Application) : AndroidViewModel(application){

    //nos creamos un objeto de tipo trainer
    private var trainer: Trainer = Trainer()

    //variable livedata para controlar el ejercicio
    lateinit var exerciseLiveData : LiveData<Int>

    //variable livedata para controlar las repeticiones
    lateinit var repetitionLiveData : LiveData<String>

    //nos creamos una variable para almacenar el ejercicio realizado(para no repetir?)
    private var previousExercise: String =""

    init {

        exerciseLiveData = Transformations.switchMap(trainer.orderLiveData,){
            exercise ->
            val mExercise = exercise.split(":")[0]
            if(mExercise != previousExercise){
                previousExercise = mExercise

                var imageID:Int = when(mExercise){
                    "EXERCISE1" -> R.drawable.e1
                    "EXERCISE2" -> R.drawable.e2
                    "EXERCISE3" -> R.drawable.e3
                    "EXERCISE4" -> R.drawable.e4
                    else -> R.drawable.e1
                }
                return@switchMap MutableLiveData<Int>(imageID)
            }

            return@switchMap null
        }

        repetitionLiveData = Transformations.switchMap(trainer.orderLiveData) { exercise ->
            return@switchMap MutableLiveData<String>(exercise.split(":")[1])
        }
    }
}