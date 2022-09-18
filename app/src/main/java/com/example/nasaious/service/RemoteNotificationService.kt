package com.example.nasaious.service

interface RemoteNotificationService {
    fun onNotificationReceived(title: String, message: String)
}
