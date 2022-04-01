package com.jovian.ficheros

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.jovian.ficheros.databinding.ActivityMainBinding
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //variable para almacenar los elementos
    private lateinit var binding: ActivityMainBinding

    private val filepath = "MyFileStorage"

    internal var myExternalFile: File?=null

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)
    }

    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(extStorageState)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cargamos el binding con los elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarInterno.setOnClickListener {
            val file:String = binding.etNombreFichero.text.toString()
            val data:String = binding.etDatos.text.toString()
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            binding.etNombreFichero.text.clear()
            binding.etDatos.text.clear()
        }

        binding.btnLeerInterno.setOnClickListener{
            val filename = binding.etNombreFichero.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                binding.etDatos.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
            }
        }

        binding.btnGuardarExterno.setOnClickListener {
            myExternalFile = File(getExternalFilesDir(filepath), binding.etNombreFichero.text.toString())
            try {
                val fileOutPutStream = FileOutputStream(myExternalFile)
                fileOutPutStream.write(binding.etDatos.text.toString().toByteArray())
                fileOutPutStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_SHORT).show()
        }

        binding.btnLeerExterno.setOnClickListener {
            myExternalFile = File(getExternalFilesDir(filepath), binding.etNombreFichero.text.toString())

            val filename = binding.etNombreFichero.text.toString()
            myExternalFile = File(getExternalFilesDir(filepath),filename)
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream =FileInputStream(myExternalFile)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                fileInputStream.close()
                //Displaying data on EditText
                Toast.makeText(applicationContext,stringBuilder.toString(),Toast.LENGTH_SHORT).show()
            }
        }

        if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
            binding.btnGuardarExterno.isEnabled = false
        }

        binding.btnLoadPreferences.setOnClickListener { loadPreference() }
        binding.btnSavePreferences.setOnClickListener { savePreference() }

        loadPreference()



    }

    fun savePreference() {
        /*val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putString(getString(R.string.key_preference),
                binding.etInfo.text.toString())
            apply()
        }*/

        val sharedPref2 = getSharedPreferences(
            getString(R.string.my_shared_file),
            Context.MODE_PRIVATE
        )
        with(sharedPref2.edit()){
            putString(getString(R.string.key_preference),
                binding.etDatos.text.toString())
            apply()
        }

    }

    private fun loadPreference() {
        //val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val sharedPref2 = getSharedPreferences(
            getString(R.string.my_shared_file),
            Context.MODE_PRIVATE
        )
        val name = sharedPref2.getString(getString(R.string.key_preference), "No saved")
        binding.tvInfo.text = name
    }

}