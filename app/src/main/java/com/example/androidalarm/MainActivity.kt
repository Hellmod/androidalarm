package com.example.androidalarm

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.androidalarm.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val NO_ALARM_TEXT = "Nenhum alarme agendado"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelButton.setOnClickListener {
            cancelAlarms()
        }

        binding.okButton.setOnClickListener{
            scheduleAlarm(binding.timeText.text.toString().toLong())
        }
    }

    private fun scheduleAlarm(seconds: Long) {
        val alarm = Calendar.getInstance()
        alarm.add(Calendar.SECOND, seconds.toInt())
        val time = SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(alarm.time)
        AlarmHelper().startAlarmManager(this, seconds)
        val text = "Alarme programado para as $time"
        binding.alarmScheduledTo.text = text
    }

    private fun cancelAlarms() {
        AlarmPlayer.instance.stop()
        setText(NO_ALARM_TEXT)
        NotificationHelper().dismissNotification(this)
        AlarmHelper().stopAlarmManager(this)
    }

    private fun setText(text: String?) {
        if (!TextUtils.isEmpty(text)) {
            binding.alarmScheduledTo.text = text
        }
    }
}