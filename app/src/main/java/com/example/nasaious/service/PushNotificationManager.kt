package com.example.nasaious.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.nasaious.R
import com.example.nasaious.screen.MainActivity
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
import javax.inject.Inject

class PushNotificationManager @Inject constructor(private val appContext: Context) {
    data class NotificationOptions(
        val intentAction: String? = null,
        val cancellable: Boolean = true,
        val cancelOnClick: Boolean = true
    )

    private val notificationManager by lazy {
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun createNotification(
        notificationChannelId: String,
        notificationChannelName: String,
        notificationMessage: String,
        notificationTitle: String? = null,
        options: NotificationOptions = NotificationOptions()
    ): Notification {
        val titleText =
            notificationTitle ?: appContext.getString(R.string.app_name)

        val channel = NotificationChannel(
            notificationChannelId,
            notificationChannelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableLights(true)
            enableVibration(true)
        }

        notificationManager.createNotificationChannel(channel)

        val launchActivityIntent = Intent(appContext, MainActivity::class.java)
        launchActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        if (options.intentAction != null) {
            launchActivityIntent.action = options.intentAction
        }

        return NotificationCompat.Builder(appContext, notificationChannelId)
            .setAutoCancel(options.cancelOnClick)
            .setOngoing(!options.cancellable)
            .setWhen(0)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    appContext.resources,
                    R.mipmap.ic_launcher_round
                )
            )
            .setContentIntent(
                PendingIntent.getActivity(
                    appContext,
                    0,
                    launchActivityIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))
            .setContentText(notificationMessage)
            .setContentTitle(titleText)
            .setColor(ContextCompat.getColor(appContext, R.color.primary))
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationMessage))
            .build()
    }

    fun notify(notificationTag: String, notification: Notification) {
        notificationManager.notify(notificationTag, 0, notification)
    }
}