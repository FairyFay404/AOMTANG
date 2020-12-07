package com.example.appaomtang

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationManagerCalling (var context: Context,var notification:Notification.Builder){
    val NotificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    lateinit var notificationChanel:NotificationChannel

    fun createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChanel = NotificationChannel(channel_ID,"notification",android.app.NotificationManager.IMPORTANCE_DEFAULT)
            NotificationManager.createNotificationChannel(notificationChanel)
        }
    }
    fun startNotification(){
        NotificationManager.notify(0,notification.build())
    }
}