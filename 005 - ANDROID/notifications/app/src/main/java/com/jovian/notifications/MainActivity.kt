package com.jovian.notifications

import android.app.Notification
import android.app.PendingIntent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import com.jovian.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pendingIntent: PendingIntent by lazy { makePendingIntent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    //cosntruccion de la notificacion
    private fun makeNotification() {

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, CHANNEL_ID)

        with(builder){
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle("Notification example")
            setContentText("This is my notification")
            setStyle(NotificationCompat.BigTextStyle().bigText("This is a long text that can't it into a single line." +
                    "This is a long text that can't it into a single line. This is a long text that can't it into a single line."))

            color = Color.RED
            priority = NotificationCompat.PRIORITY_DEFAULT

            //1 sec on 1 sec off
            setLights(Color.MAGENTA, 1000, 1000)

            //1 sec on 1 sec off 1 sec on 1 sec off
            setVibrate(longArrayOf(1000, 1000, 1000, 1000))

            //setSound
            setDefaults(Notification.DEFAULT_SOUND)

            //when we click on the notification
            setContentIntent(pendingIntent)

            setVisibility(VISIBILITY_PUBLIC)

            addAction(R.dra)






        }
    }

    private fun makePendingIntent() {
        TODO("Not yet implemented")
    }
}

