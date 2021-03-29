package com.shahcement.tawfiq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.shahcement.tawfiq.AppConstants
import com.shahcement.tawfiq.databinding.ActivityMainBinding
import com.shahcement.tawfiq.fragment.PrayerFragment
import com.shahcement.tawfiq.fragment.RamadanFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariable()
        initListener()
    }

    private fun initVariable() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        val defaultData = mutableMapOf<String, Any>()
        defaultData["ramadan_start_date"] = "2021-04-15"
        defaultData["ramadan_end_date"] = "2021-05-13"

        remoteConfig.setDefaultsAsync(defaultData)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) {
                val start = remoteConfig.getString("ramadan_start_date")
                val end = remoteConfig.getString("ramadan_end_date")

                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                try {
                    val startDate = format.parse(start)
                    val endDate = format.parse(end)

                    val isRamadan = if (startDate != null && endDate != null) {
                        startDate.equals(Date()) || startDate.before(Date()) && endDate.before(Date())
                    } else {
                        false
                    }

                    initView(isRamadan)

                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
    }

    private fun initView(isRamadan: Boolean) {
        if (isRamadan) {
            binding.headerTabs.lytPrayer.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, RamadanFragment())
                .commit()
        } else {
            binding.headerTabs.lytPrayer.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, PrayerFragment())
                .commit()
        }
    }

    private fun initListener() {
        binding.headerTabs.lytPrayer.setOnClickListener {
            startContainerActivity(AppConstants.PRAYER)
        }

        binding.headerTabs.lytSura.setOnClickListener {
            startContainerActivity(AppConstants.SURA)
        }

        binding.headerTabs.lytDua.setOnClickListener {
            startContainerActivity(AppConstants.DUA)
        }

        binding.headerTabs.lytTasbeeh.setOnClickListener {
            startContainerActivity(AppConstants.TASBEEH)
        }

        binding.headerTabs.lytQibla.setOnClickListener {
            startContainerActivity(AppConstants.QIBLA)
        }
    }

    private fun startContainerActivity(id: Int) {
        startActivity(
            Intent(this, ContainerActivity::class.java)
                .putExtra("view_id", id)
        )
    }
}