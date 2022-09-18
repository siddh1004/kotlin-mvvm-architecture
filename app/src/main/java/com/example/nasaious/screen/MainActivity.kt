package com.example.nasaious.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaious.databinding.ActivityMainBinding
import com.example.nasaious.service.FirebaseMessageService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String =
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        setUpFirebaseMessaging(id)
    }

    private fun setUpFirebaseMessaging(deviceId: String) {
        FirebaseMessageService.getToken().observeForever { token ->
            println("Device ID is $deviceId")
            println("token is $token")

        }
    }
}