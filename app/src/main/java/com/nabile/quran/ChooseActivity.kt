package com.nabile.quran

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        sourateButton.setOnClickListener {
            val intent = Intent(this, ListeSouratesActivity::class.java)
            startActivity(intent)
        }

        resumeButton.setOnClickListener {
            val intent = Intent(this, SourateActivity::class.java)
            val sharedPref = applicationContext.getSharedPreferences("savedSourate", MODE_PRIVATE)
            val sourate = sharedPref.getInt("sourate", -10)
            val verse = sharedPref.getInt("verse", -10)
            val title = sharedPref.getString("title", "Sourate")

            intent.putExtra("sourate", sourate.toString())
            intent.putExtra("verse", verse.toString())
            intent.putExtra("title", title)

            startActivity(intent)
        }
    }

}