package com.example.assignment2

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.SeekBar

class AudioFragment : Fragment(R.layout.fragment_audio) {

    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPlay = view.findViewById<Button>(R.id.btnPlayAudio)
        val btnPause = view.findViewById<Button>(R.id.btnPauseAudio)
        val btnStop = view.findViewById<Button>(R.id.btnStopAudio)
        val btnReset = view.findViewById<Button>(R.id.btnResetAudio)



        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sample_audio)

        btnPlay.setOnClickListener {
            mediaPlayer?.start()
        }

        btnPause.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        btnStop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sample_audio)
        }

        btnReset.setOnClickListener {
            mediaPlayer?.seekTo(0)
            mediaPlayer?.pause()
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sample_audio)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}