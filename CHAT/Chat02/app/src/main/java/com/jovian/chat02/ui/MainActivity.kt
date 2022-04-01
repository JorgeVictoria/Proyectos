package com.jovian.chat02.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jovian.chat02.R
import com.jovian.chat02.adapter.MessagingAdapter
import com.jovian.chat02.model.Message
import com.jovian.chat02.utils.Constants.CHANNEL_ID
import com.jovian.chat02.utils.Constants.CHANNEL_NAME
import com.jovian.chat02.utils.Constants.NOTIFICATION_ID
import com.jovian.chat02.utils.Constants.PENDING_REQUEST
import com.jovian.chat02.utils.Constants.RECEIVE_ID
import com.jovian.chat02.utils.Constants.SEND_ID
import com.jovian.chat02.utils.Time
import kotlinx.coroutines.*

private const val TAG = "MyBroadcastReceiver"

class MainActivity : AppCompatActivity() {

    //variables globales para recoger los elementos del layout y darles funcionalidad
    private val btn_send: Button by lazy { findViewById(R.id.btn_send) }
    private val et_message: EditText by lazy { findViewById(R.id.et_message) }
    private val rv_messages: RecyclerView by lazy { findViewById(R.id.rv_messages) }

    //variable para gestion de notificaciones
    private val pendingIntent: PendingIntent by lazy { makePendingIntent() }

    //listado local de mis mensajes, para prueba
    var messagesList = mutableListOf<Message>()

    //variable global para acceder al adapter
    private lateinit var adapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //llamado al recycler
        recyclerView()

        //llamada al evento para el control de envio de mensajes
        clickEvents()

        //creamos una instancia de nuestro broadcastReceiver
        val br: BroadcastReceiver = MyBroadcastReceiver()

        //variable intentfilter para filtrar que tipo de accion debemos recoger para la recepcion de mensajes. Coincide con el manifest
        //importante que el action que el nombre del action no coincida con el del otro chat, para evitar cruce de mensajeria
        val intentFilter = IntentFilter("com.jovian.key.chat01")
        if (intentFilter != null) {
            registerReceiver(br, intentFilter)
        }

        //instancia para poder pasar a nuestra activity los mensajes recibidos en el broadcaster
        LocalBroadcastManager.getInstance(this).registerReceiver(
            myMessageReceiver,
            IntentFilter("miMsg")
        );

    }

    //Objeto broadcastreceiver para poder trabajar con el mensaje recibido
    //desgranamos el intent y enviamos el mensaje a la funcion que muestra la respuesta en pantalla
    //a su vez, activamos la notificacion de que hemos recibido un mensaje
    private val myMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.getStringExtra("Mensaje")//Get message
            if (message != null) {
                myResponse(message)
                sendNotification(message)
            }
        }
    }

    //diferentes listener para controlar eventos en pantalla
    private fun clickEvents() {

        //envio del mensaje
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back a la posicion correcta cuando el usuario hace click en un text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    //carga del recyclerView
    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //si hay mensajes, nos iremos a la posicion final al reabrir la aplicacion
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    //cuando pulsamos sobre el boton send
    //imprimimos en pantalla el mensaje
    //iniciamos el envio del mismo mensaje a traves de broadcast
    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            enviarMensaje(message)
        }
    }

    //envio del mensaje a traves del broadcast
    //previamente llamamos al intent
    private fun enviarMensaje(message: String) {
        sendBroadcast(makeIntent(message))
    }

    //funcion para crear el intent. Añadimos:
    //identificador de que chat envia junto al mensaje
    //funcion que tiene el otro chat para saber que recibe algo
    private fun makeIntent(message: String): Intent? {
        Log.d("intent", "estoy en el intent")
        val intento = Intent()
        intento.putExtra("chat02", message)
        intento.action = "com.jovian.key.chat02"
        intento.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        applicationContext.sendBroadcast(intento)
        return intent

    }


    //funcion para imprimir en pantalla los mensajes que recibimos
    private fun myResponse(message: String) {

        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = message

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)

            }
        }
    }

    //clase para la gestion de recepcion de mensajes
    class MyBroadcastReceiver : BroadcastReceiver() {

        private lateinit var context: Context

        //metodo para recepcionar el intent, desgranarlo y coger el mensaje
        override fun onReceive(contexto: Context, intent: Intent) {

            context = contexto
            //get the string of the put extra of the indent of the other app
            val data = intent!!.getStringExtra("chat01")
            Toast.makeText(context, data, Toast.LENGTH_LONG).show()
            if (data != null) {
                sendMessage(data)
            }
        }

        //Esta funcion permite pasar el mensaje recibido a nuestro main activity
        private fun sendMessage(string: String) {
            val intent = Intent("miMsg")
            intent.putExtra("Mensaje", string)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }


    }

    //funcion para el envio de notificaciones
    private fun sendNotification(s: String?) {
        CoroutineScope(Dispatchers.Default).launch {
            delay(600L)
            makeNotificationChannel()
            makeNotificaton(s)
        }
    }


    //funcion para generar el channel de las notificicaciones
    private fun makeNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationChannel.setShowBadge(true)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    //funcion para crear notificaciones
    private fun makeNotificaton(s: String?) {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(
                this,
                CHANNEL_ID
            )

        with(builder) {
            setSmallIcon(android.R.drawable.ic_popup_reminder)
            setContentTitle("Chat01").color = Color.RED
            var xd = s?.substring(s.indexOf('\n') + 1);

            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(xd)
            )

            setAutoCancel(true)
            color = Color.RED
            priority = NotificationCompat.PRIORITY_DEFAULT

            setLights(Color.MAGENTA, 1000, 1000)

            setVibrate(longArrayOf(1000, 1000, 1000, 1000))

            setDefaults(Notification.DEFAULT_SOUND)

            setContentIntent(pendingIntent)

            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            setFullScreenIntent(pendingIntent, true)

        }

        val notificationManagerCompat = NotificationManagerCompat.from(this)

        //lanzamiento de la notificacion
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
    }

    private fun makePendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)

        return PendingIntent.getActivity(this, PENDING_REQUEST, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}