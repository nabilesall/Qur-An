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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sourate)

        title = intent?.extras?.getString("title").toString()
        ajizaPosition = intent?.extras?.getString("ajizaPosition").toString().toInt()

        sourateTitleBar.text = title

        listOfVerse = ArrayList()
        versesAdapter = VersesAdapter(this, listOfVerse)
        versesRecyclerView.adapter = versesAdapter
        versesRecyclerView.layoutManager = LinearLayoutManager(this)
        //methode pour remplir la liste des sourates
        versesAdapter.notifyDataSetChanged()
    }

    private fun getVerses(ajizaPosition: Int) {
        val inputStream : InputStream = assets.open("ajiza.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)

        try {
            val jsonArray = jsonObject.getJSONArray("ajiza")
            val currentSourate = jsonArray.getJSONObject(ajizaPosition)
            val verses = currentSourate.getJSONArray("versets")
            for (i in 0..verses.length()){
                val verse = verses.getJSONObject(i)


            }
        }catch (e: Exception){
            Log.e("Error", e.toString())
        }
    }
}