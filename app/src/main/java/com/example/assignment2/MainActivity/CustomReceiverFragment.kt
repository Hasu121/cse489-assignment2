package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CustomReceiverFragment : Fragment(R.layout.fragment_custom_receiver) {

    private lateinit var tvReceivedMessage: TextView
    private lateinit var btnBackFromReceiver: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvReceivedMessage = view.findViewById(R.id.tvReceivedMessage)
        btnBackFromReceiver = view.findViewById(R.id.btnBackFromReceiver)

        val message = arguments?.getString(ARG_MESSAGE).orEmpty()
        tvReceivedMessage.text = message

        btnBackFromReceiver.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val ARG_MESSAGE = "arg_message"

        fun newInstance(message: String): CustomReceiverFragment {
            val fragment = CustomReceiverFragment()
            val bundle = Bundle()
            bundle.putString(ARG_MESSAGE, message)
            fragment.arguments = bundle
            return fragment
        }
    }
}