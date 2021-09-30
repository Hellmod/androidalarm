package com.example.androidalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class StartAlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        AlarmPlayer.instance.playAlarm(context!!)
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://smak-chleba.pl/"))
        i.addFlags(FLAG_ACTIVITY_NEW_TASK)
        startActivity(context,i,null)
    }
}