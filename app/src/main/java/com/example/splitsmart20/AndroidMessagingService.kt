package com.example.splitsmart20.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FirebaseMessagingService"
        const val CHANNEL_ID = "splitsmart_notifications"
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        // Send this token to the server
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let { notification ->
            val title = notification.title ?: "SplitSmart"
            val body = notification.body ?: "New notification"

            Log.d(TAG, "Message Notification Title: $title")
            Log.d(TAG, "Message Notification Body: $body")

            // Show the notification
            sendNotification(title, body)
        }

        // Also check for data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            // Handle data message here
        }
    }

    private fun sendRegistrationToServer(token: String) {

        // TODO: Implement this method to send token to app server

        Log.d(TAG, "FCM Token: $token")

        // You can save this token to Firebase Database or your backend
        // Example: saveTokenToDatabase(token)
    }

    private fun sendNotification(title: String, messageBody: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "SplitSmart Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for SplitSmart notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Change to your app icon
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }
}

