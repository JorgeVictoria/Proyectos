package com.jovian.pcamera

import android.Manifest
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jovian.pcamera.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

typealias LumaListener = (luma: Double)->Unit
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private var imageCapture: ImageCapture? = null

    private lateinit var outputDirectory: File //Directory where we are going to save our image
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var rutaFoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it}.root)

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Set up the listener for take photo button
        binding.cameraCaptureButton.setOnClickListener { takePhoto() }

        //Set the directory where we're going to save our image
        outputDirectory = getOutputDirectory()

        //Executor that do the task in background
        cameraExecutor = Executors.newSingleThreadExecutor()
    }


    private fun takePhoto() {
        //First, get a reference to the ImageCapture use case.
        // If the use case is null, exit out of the function.
        // This will be null If you tap the photo button before image capture is set up.
        // Without the return statement, the app would crash if it was null
        val imageCapture = imageCapture ?: return

        //create a file to hold the image. Add in a time stamp so the file name will be unique.
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg")

        //Create an OutputFileOptions object.
        // This object is where you can specify things about how you want your output to be.
        // You want the output saved in the file we just created, so add your photoFile
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        //Call takePicture() on the imageCapture object.
        // Pass in outputOptions, the executor, and a callback for when the image is saved.
        // You'll fill out the callback next.
        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                // The photo was taken successfully! Save the photo to the file you created earlier,
                // present a toast to let the user know it was successful, and print a log statement.
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    rutaFoto = savedUri.toString()
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                }

                //In the case that the image capture fails or saving the image capture fails, add in an error case to log that it failed.
                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                }
            }
        )

    }

    private fun startCamera() {
        //Create an instance of the ProcessCameraProvider. This is used to bind the lifecycle
        // of cameras to the lifecycle owner. This eliminates the task of opening and closing the camera since
        // CameraX is lifecycle-aware.
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        //Add a listener to the cameraProviderFuture.
        //Runnable as one argument.
        //ContextCompat.getMainExecutor() as the second argument. This returns an Executor that runs on the main thread.
        cameraProviderFuture.addListener(Runnable {

            //ProcessCameraProvider. This is used to bind the lifecycle of your camera to the LifecycleOwner
            // within the application's process.
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            //Initialize your Preview object, call build on it,
            // get a surface provider from viewfinder, and then set it on the preview.
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
                        Log.d(TAG, "Average luminosity: $luma")
                    })
                }

            //Create a CameraSelector object and select DEFAULT_BACK_CAMERA.
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            //Create a try block. Inside that block, make sure nothing is bound to your cameraProvider,
            // and then bind your cameraSelector and preview object to the cameraProvider.
            //There are a few ways this code could fail, like if the app is no longer in focus.
            // Wrap this code in a catch block to log if there's a failure.
            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview,imageCapture,imageAnalyzer)
            }catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))



    }

    //Checks if all permissions are granted to us, at the moment we only check CAMERA permission
    private fun allPermissionsGranted(): Boolean  = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists()) //If external storage is available we use it otherwise we use internal
            mediaDir else filesDir
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //Check if the request code is correct; ignore it otherwise.
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) { //If the permissions are granted, call startCamera().
                startCamera()
            } else {
                //If permissions are not granted, present a toast to notify the user that the permissions were not granted.
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }



    //Some Static constants
    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

        //Code for requesting permissions, we check it in onRequestPermissionsResult
        private const val REQUEST_CODE_PERMISSIONS = 10

        //Permissions that we're going to use
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
    }

}