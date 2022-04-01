package com.jovian.activities2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.jovian.activities2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //para almacener los elementos de la vista
    private lateinit var binding: ActivityMainBinding

    //para recoger la informacion que nos devuelve la activity2
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Resultado Correcto", Toast.LENGTH_LONG).show()
        }
        else Toast.makeText(this, "Resultado Erroneo", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cargamos el binding con los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        //le ponemos el listener al boton y llamamos a la funcion
        binding.btnLogin.setOnClickListener() {

            //nos creamos 2 variables donde recogemos los datos introducidos
            val nombre:String = binding.etName.text.toString()
            val apellido:String = binding.etSurName.text.toString()

            //construimos el objeto intent para llamar a la activity
            val intent = Intent(this, Activity2::class.java)

            //metemos 2 putEXtra con los datos que queremos pasar
            intent.putExtra("Nombre", nombre)
            intent.putExtra("apellido", apellido)

            //Si queremos recibir un resultado
            resultLauncher.launch(intent)
            //si solo queremos llamar a la otra activity
            //startActivity(intent)
        }
    }
}