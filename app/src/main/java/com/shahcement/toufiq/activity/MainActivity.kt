package com.shahcement.toufiq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shahcement.toufiq.AppConstants
import com.shahcement.toufiq.databinding.ActivityMainBinding
import com.shahcement.toufiq.fragment.PrayerFragment
import com.shahcement.toufiq.fragment.RamadanFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    private fun initView() {
        val isRamadan = true

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