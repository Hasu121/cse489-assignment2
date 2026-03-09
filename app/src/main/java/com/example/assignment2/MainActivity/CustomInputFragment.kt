package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class CustomInputFragment : Fragment(R.layout.fragment_custom_input) {

    private lateinit var etCustomMessage: EditText
    private lateinit var btnSendBroadcast: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etCustomMessage = view.findViewById(R.id.etCustomMessage)
        btnSendBroadcast = view.findViewById(R.id.btnSendBroadcast)

        btnSendBroadcast.setOnClickListener {
            val message = etCustomMessage.text.toString().trim()

            if (message.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fragment = CustomReceiverFragment.newInstance(message)
            (activity as MainActivity).replaceFragment(fragment, true)
        }
    }
}