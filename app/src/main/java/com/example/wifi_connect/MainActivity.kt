package com.example.wifi_connect


import androidx.activity.enableEdgeToEdge

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var btnToggleWifi: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        btnToggleWifi = findViewById(R.id.btnToggleWifi)
        tvStatus = findViewById(R.id.tvStatus)

        updateStatus()

        btnToggleWifi.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android 10 and above, open Wi-Fi settings instead
                Toast.makeText(this, "Cannot toggle Wi-Fi directly on Android 10+.", Toast.LENGTH_SHORT).show()
                val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                startActivity(panelIntent)
            } else {
                val newState = !wifiManager.isWifiEnabled
                wifiManager.isWifiEnabled = newState
                updateStatus()
            }
        }
    }

    private fun updateStatus() {
        val status = if (wifiManager.isWifiEnabled) "ON" else "OFF"
        tvStatus.text = "Wi-Fi is $status"
    }
}
