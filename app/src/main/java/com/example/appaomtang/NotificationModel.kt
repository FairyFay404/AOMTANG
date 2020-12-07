package com.example.appaomtang

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

var channel_ID="com.example.appaomtang"
class NotificationModel (var context: Context,var title:String,var content: String){

    val intent=Intent(context.applicationContext,note::class.java)
    val pendingintent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

    fun createNotification():Notification.Builder{
        val builder= if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder(context, channel_ID).apply {
                setContentTitle(title)
                setContentText(content)
                setSmallIcon(R.drawable.ic2)
                setContentIntent(pendingintent)
            }
        } else {
            Notification.Builder(context).apply {
                setContentTitle("title")
                setContentText("this is content")
                setSmallIcon(R.drawable.ic2)
                setContentIntent(pendingintent)
            }
        }
        return builder
    }

}