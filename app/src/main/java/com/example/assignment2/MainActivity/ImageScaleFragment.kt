package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class ImageScaleFragment : Fragment(R.layout.fragment_image_scale) {

    private var scaleFactor = 1.0f
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var imageView: ImageView
    private lateinit var btnPickImage: Button
    private var selectedImageUri: Uri? = null

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                imageView.setImageURI(selectedImageUri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.ivScalableImage)
        btnPickImage = view.findViewById(R.id.btnPickImage)

        btnPickImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePickerLauncher.launch(intent)
        }

        scaleGestureDetector = ScaleGestureDetector(
            requireContext(),
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = scaleFactor.coerceIn(0.5f, 3.0f)

                    imageView.scaleX = scaleFactor
                    imageView.scaleY = scaleFactor
                    return true
                }
            }
        )

        imageView.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }
}