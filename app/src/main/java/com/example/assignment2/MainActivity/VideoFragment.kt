package com.example.assignment2

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoFragment : Fragment(R.layout.fragment_video) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView = view.findViewById<VideoView>(R.id.videoView)
        val btnPlay = view.findViewById<Button>(R.id.btnPlayVideo)
        val btnPause = view.findViewById<Button>(R.id.btnPauseVideo)
        val btnReset = view.findViewById<Button>(R.id.btnResetVideo)

        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.sample_video}")
        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener { mediaPlayer: MediaPlayer ->
            mediaPlayer.setVolume(1.0f, 1.0f)
        }

        btnPlay.setOnClickListener {
            videoView.start()
        }

        btnPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        btnReset.setOnClickListener {
            videoView.stopPlayback()
            videoView.setVideoURI(videoUri)
            videoView.start()
        }
    }
}