package com.shahcement.toufiq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shahcement.toufiq.AppConstants
import com.shahcement.toufiq.databinding.ActivityContainerBinding
import com.shahcement.toufiq.databinding.LayoutHeaderTabsBinding
import com.shahcement.toufiq.fragment.*

class ContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, QiblaFragment())
                    .commit()
            }
        }
    }
}