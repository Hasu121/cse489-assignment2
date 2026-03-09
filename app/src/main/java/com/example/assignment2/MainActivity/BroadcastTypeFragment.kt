package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class BroadcastTypeFragment : Fragment(R.layout.fragment_broadcast_type) {

    private lateinit var spinnerBroadcastType: Spinner
    private lateinit var btnProceedBroadcast: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerBroadcastType = view.findViewById(R.id.spinnerBroadcastType)
        btnProceedBroadcast = view.findViewById(R.id.btnProceedBroadcast)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.broadcast_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBroadcastType.adapter = adapter

        btnProceedBroadcast.setOnClickListener {
            val selected = spinnerBroadcastType.selectedItemPosition

            val nextFragment = if (selected == 0) {
                CustomInputFragment()
            } else {
                BatteryReceiverFragment()
            }

            (activity as MainActivity).replaceFragment(nextFragment, addToBackStack = true)
        }
    }
}