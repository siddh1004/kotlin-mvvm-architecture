package com.example.nasaious.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaious.BuildConfig
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessageService :
    FirebaseMessagingService(), RemoteNotificationService {

    @Inject
    lateinit var notificationManager: PushNotificationManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) =
        onNotificationReceived(remoteMessage.title, remoteMessage.message)

    override fun onNewToken(token: String) {
    }

    override fun onNotificationReceived(title: String, message: String) {
        val notification = notificationManager.createNotification(
            REMOTE_NOTIFICATION_CHANNEL_ID,
            REMOTE_NOTIFICATION_CHANNEL_NAME,
            message,
            title
        )
        notificationManager.notify(title, notification)
    }

    private val RemoteMessage.title
        get() = requireNotNull(notification?.title)

    private val RemoteMessage.message
        get() = requireNotNull(notification?.body)

    companion object {
        private const val REMOTE_NOTIFICATION_CHANNEL_ID =
            "${BuildConfig.APPLICATION_ID}.id.remote_notification_channel"
        private const val REMOTE_NOTIFICATION_CHANNEL_NAME = "Updates"

        fun getToken(): LiveData<String> {
            val token = MutableLiveData<String>()
            FirebaseMessaging.getInstance().token.addOnSuccessListener { _token ->
                token.postValue(_token)
            }
            return token
        }
    }
}