package com.nabile.quran

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sourate.*
import org.json.JSONObject
import java.io.InputStream

class SourateActivity : AppCompatActivity() {

    lateinit var listOfVerse : ArrayList<VerseObject>
    lateinit var versesAdapter : VersesAdapter
    lateinit var title : String
    var souratePosition : Int = -10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sourate)

        title = intent?.extras?.getString("title").toString()
        souratePosition = intent?.extras?.getString("sourate").toString().toInt()

        sourateTileBar.text = title

        listOfVerse = ArrayList()
        versesAdapter = VersesAdapter(this, listOfVerse)
        versesRecyclerView.adapter = versesAdapter
        versesRecyclerView.layoutManager = LinearLayoutManager(this)

        getVerses(souratePosition - 1)

        versesAdapter.notifyDataSetChanged()

    }

    private fun getVerses(souratePosition: Int) {
        val inputStream : InputStream = assets.open("quran.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        var jsonObject = JSONObject(json)

        try {
            var jsonArray = jsonObject.getJSONArray("sourates")
            val currentSourate = jsonArray.getJSONObject(souratePosition)
            val verses = currentSourate.getJSONArray("versets")
            for (i in 0..verses.length()){
                val verse = verses.getJSONObject(i)

                val verseNumber = verse.getInt("position_ds_sourate")
                val verseInFrench = verse.getString("text")
                val verseInArabic = verse.getString("text_arabe")
                var verseObject = VerseObject(verseNumber, verseInArabic, verseInFrench)

                listOfVerse.add(verseObject)
            }


        }catch (e: Exception){
            Log.e("Error", e.toString())
        }

    }

}