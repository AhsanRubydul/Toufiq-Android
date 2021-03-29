package com.shahcement.tawfiq.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shahcement.tawfiq.AppConstants
import com.shahcement.tawfiq.databinding.ActivityContainerBinding
import com.shahcement.tawfiq.fragment.*

class ContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = ""

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        when (intent?.extras?.getInt("view_id", -1)) {
            AppConstants.PRAYER -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, PrayerFragment())
                    .commit()
            }
            AppConstants.SURA -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, SuraFragment())
                    .commit()
            }
            AppConstants.DUA -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, DuaFragment())
                    .commit()
            }
            AppConstants.TASBEEH -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, TasbeehFragment())
                    .commit()
            }
            AppConstants.QIBLA -> {
                val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                val magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

                if (accelerometerSensor != null && magnetometerSensor != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, QiblaFragment())
                        .commit()
                } else {
                    Toast.makeText(this, "কম্পাস সেনসর নেই", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}