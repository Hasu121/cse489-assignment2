package com.example.assignment2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class BatteryReceiverFragment : Fragment(R.layout.fragment_battery_receiver) {

    private lateinit var tvBatteryPercentage: TextView

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

            if (level != -1 && scale != -1) {
                val percentage = (level * 100) / scale
                tvBatteryPercentage.text = "$percentage%"
            } else {
                tvBatteryPercentage.text = "Battery unavailable"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBatteryPercentage = view.findViewById(R.id.tvBatteryPercentage)
    }

    override fun onStart() {
        super.onStart()
        requireContext().registerReceiver(
            batteryReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(batteryReceiver)
    }
}