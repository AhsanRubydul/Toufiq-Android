package com.shahcement.toufiq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shahcement.toufiq.R
import com.shahcement.toufiq.fragment.DuaFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DuaFragment())
            .commit()
    }
}