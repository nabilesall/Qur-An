package com.nabile.quran

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedP = applicationContext.getSharedPreferences("savedSettings", MODE_PRIVATE)
        val toFrench = sharedP.getBoolean("toFrench", true)
        toFrenchSwitch.isChecked = toFrench

    }

    override fun onStop() {
        super.onStop()

        val sharedP = applicationContext.getSharedPreferences("savedSettings", MODE_PRIVATE)
        val editor = sharedP.edit()
        editor.putBoolean("toFrench", toFrenchSwitch.isChecked)
        editor.apply()
    }
}