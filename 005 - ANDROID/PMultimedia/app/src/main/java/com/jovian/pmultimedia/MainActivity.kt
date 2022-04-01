package com.jovian.pmultimedia

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.jovian.pmultimedia.databinding.ActivityMainBinding
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var mediaPlayer: MediaPlayer? = null
    var songlist = intArrayOf(R.raw.jungle, R.raw.brownstone, R.raw.crazy, R.raw.easy,
                              R.raw.goes, R.raw.michelle, R.raw.nightrain, R.raw.out,
                              R.raw.paradise, R.raw.queen, R.raw.sweet, R.raw.think)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
    }

    fun music(view: View){
        when (view.id) {
            R.id.btnPlay -> {
                //Check if mediaPlayer is null. If true, we'll instantiate the MediaPlayer object
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, songlist[0])
                }
                //Then, register OnCompletionListener that calls a user supplied callback method onCompletion() when
                //looping mode was set to false to indicate playback is completed.
                mediaPlayer!!.setOnCompletionListener { //here, call a method to release the mediaPLayer object and to set it to null
                    stopMusic()
                }

                //Next, call start() method on mediaPlayer to start playing the music.

                mediaPlayer!!.start()
                initializeSeekBar()
            }

            R.id.btnPause -> if (mediaPlayer != null) {
                //Here, call pause() method on MediaPlayer to pause the music
                mediaPlayer!!.pause()
            }

            R.id.btnStop -> if (mediaPlayer != null) {
                //here, call stop() method on mediaPlayer to stop the music
                mediaPlayer!!.stop()
                //call stopMusic() method
                stopMusic()
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun initializeSeekBar() {
        
        binding.seekBar.max = mediaPlayer!!.duration
        
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this,1000)
                } catch (e: Exception){
                    binding.seekBar.progress = 0
                }
            }
        },0)
    }

    private fun stopMusic() {
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    // Call stopMusic() in onStop() overridden method as well.
    override fun onStop() {
        super.onStop()
        stopMusic()
    }
}

