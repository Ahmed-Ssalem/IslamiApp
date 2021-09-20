package com.example.islamiapp.Fragments

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.islamiapp.Adapters.RadioChannelAdapter
import com.example.islamiapp.R
import com.example.islamiapp.api.ApiManager
import com.example.islamiapp.api.RadioResponse
import com.example.islamiapp.api.RadiosItem
import kotlinx.android.synthetic.main.fragment_radio.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RadioFragment : Fragment() {

    lateinit var adapter: RadioChannelAdapter
    var mediaPlayer: MediaPlayer? = null

    private val mCompletionListener =
        OnCompletionListener {
            //Toast.makeText(PhrasesActivity.this,"I'm done.",Toast.LENGTH_LONG).show();
            // Now that the sound file has finished playing, release the media player resources.
            stopChannel()
        }

    private var mAudioManager: AudioManager? = null
    var mOnAudioFocusChangeListener =
        OnAudioFocusChangeListener { focusChange ->
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
            ) {

                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer!!.pause()
                mediaPlayer!!.seekTo(0)

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                stopChannel()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAudioManager = activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        initRadioChannelAdapter()
        getRadioChannels()
    }



    private fun initRadioChannelAdapter() {


        // link the adapter with the recuclerView
        adapter = RadioChannelAdapter()
        radio_recycler.adapter = adapter

        // to swap slowly
        /*
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(radio_recycler)
        */

        // on click listener for buttons

        adapter.setOnPlayClickListener(object : RadioChannelAdapter.onItemClickListener {
            override fun onItemClick(position: Int, channel: RadiosItem) {
                channel.radioUrl?.let { playChannel(it) }
            }
        })

        adapter.setOnStopClickListener(object : RadioChannelAdapter.onItemClickListener {
            override fun onItemClick(position: Int, channel: RadiosItem) {
                stopChannel()
            }
        })

    }

    private fun getRadioChannels() {
        ApiManager.getApis()
            .getRadiosChannel()
            .enqueue(object : Callback<RadioResponse> {

                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {

                    progress_bar.visibility = View.GONE
                    if (response.isSuccessful) {
                        adapter.changeData(response.body()?.radios)
                    } else
                        Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun playChannel(url: String) {

        stopChannel()
        mediaPlayer = MediaPlayer()

        // Request audio focus so in order to play the audio file. The app needs to play a
        // short audio file, so we will request audio focus with a short amount of time
        // with AUDIOFOCUS_GAIN_TRANSIENT.
        val result = mAudioManager!!.requestAudioFocus(
            mOnAudioFocusChangeListener,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
        )

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

            // Start the audio file
            mediaPlayer?.setDataSource(url)
            mediaPlayer?.prepareAsync()
            mediaPlayer?.setOnPreparedListener {
                it.start()
            }

            // Setup a listener on the media player, so that we can stop and release the
            // media player once the sound has finished playing.
            mediaPlayer!!.setOnCompletionListener(mCompletionListener)
        }



//        try {
//            mediaPlayer?.setDataSource(url)
//            mediaPlayer?.prepareAsync()
//            mediaPlayer?.setOnPreparedListener {
//                it.start()
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            Toast.makeText(context, "Cannot Play Channel!!", Toast.LENGTH_LONG).show()
//        }
    }

    fun stopChannel() {
        if (mediaPlayer == null)
            return
        else {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}