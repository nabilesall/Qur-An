package com.nabile.quran

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabile.quran.adapter.VersesAdapter
import com.nabile.quran.objects.VerseObject
import kotlinx.android.synthetic.main.activity_sourate.*
import org.json.JSONObject
import java.io.InputStream


class AjizaActivity : AppCompatActivity() {

    private lateinit var listOfVerse : ArrayList<VerseObject>
    private lateinit var versesAdapter : VersesAdapter
    private lateinit var title : String
    private var ajizaPosition : Int = -10
    private var verseToRead : Int = -10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sourate)

        title = intent?.extras?.getString("title").toString()
        ajizaPosition = intent?.extras?.getString("sourate").toString().toInt()
        verseToRead = intent?.extras?.getString("verse").toString().toInt()

        sourateTitleBar.text = title

        listOfVerse = ArrayList()
        versesAdapter = VersesAdapter(this, listOfVerse)
        versesRecyclerView.adapter = versesAdapter
        versesRecyclerView.layoutManager = LinearLayoutManager(this)

        getVerses(ajizaPosition - 1)
        if (verseToRead != -10){
            versesRecyclerView.smoothScrollToPosition(verseToRead-1)
        }
        versesAdapter.notifyDataSetChanged()
    }

    private fun getVerses(ajizaPosition: Int) {
        val inputStream : InputStream = assets.open("ajiza.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)

        try {
            val jsonArray = jsonObject.getJSONArray("ajiza")
            val currentJuzu = jsonArray.getJSONObject(ajizaPosition)
            val verses = currentJuzu.getJSONArray("versets")
            for (i in 0 until verses.length()){
                val verse = verses.getJSONObject(i)

                val verseNumber = verse.getInt("position_ds_sourate")
                val verseInFrench = verse.getString("text")
                val verseInArabic = verse.getString("text_arabe")
                val verseObject = VerseObject(verseNumber, verseInArabic, verseInFrench)

                listOfVerse.add(verseObject)
            }
        }catch (e: Exception){
            Log.e("Error", e.toString())
        }
    }


    override fun onStop() {
        super.onStop()
        val layoutManager = versesRecyclerView.layoutManager as LinearLayoutManager
        val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()

        val sharedPref = applicationContext.getSharedPreferences("savedSourate", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("typeLecture", "juzu")
        editor.putString("title", title)
        editor.putInt("sourate", ajizaPosition)
        editor.putInt("verse", lastVisiblePosition)

        editor.apply()
    }
}