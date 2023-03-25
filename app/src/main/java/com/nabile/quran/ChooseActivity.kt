package com.nabile.quran

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        // Get the List of Sourates
        sourateButton.setOnClickListener {
            val intent = Intent(this, ListeSouratesActivity::class.java)
            startActivity(intent)
        }

        // Resume the last lecture
        resumeButton.setOnClickListener {
            val intent = Intent(this, SourateActivity::class.java)
            val intentJuzu = Intent(this, AjizaActivity::class.java)

            val sharedPref = applicationContext.getSharedPreferences("savedSourate", MODE_PRIVATE)
            val typeLecture = sharedPref.getString("typeLecture", "")
            val sourate = sharedPref.getInt("sourate", -10)
            val verse = sharedPref.getInt("verse", -10)
            val title = sharedPref.getString("title", "Sourate")

            if (typeLecture == "sourate") {
                intent.putExtra("sourate", sourate.toString())
                intent.putExtra("verse", verse.toString())
                intent.putExtra("title", title)

                startActivity(intent)
            }
            else if( typeLecture == "juzu"){
                intentJuzu.putExtra("sourate", sourate.toString())
                intentJuzu.putExtra("verse", verse.toString())
                intentJuzu.putExtra("title", title)

                startActivity(intentJuzu)
            }
        }

        // Get the List of Juz
        ajizaButton.setOnClickListener {
            val intent = Intent(this, ListeAjizaActivity::class.java)
            startActivity(intent)
        }
    }
}