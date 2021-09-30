package com.example.androidalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidalarm.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

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
        AlarmHelper().startAlarmManager(this, seconds)
    }

    private fun cancelAlarms() {
        AlarmPlayer.instance.stop()
        AlarmHelper().stopAlarmManager(this)
    }
}