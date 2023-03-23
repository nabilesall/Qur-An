package com.nabile.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabile.quran.adapter.SourateAdapter
import com.nabile.quran.objects.SourateObject
import kotlinx.android.synthetic.main.activity_liste_sourates.*
import org.json.JSONObject
import java.io.InputStream

class ListeSouratesActivity : AppCompatActivity() {

    private lateinit var listOfSourate : ArrayList<SourateObject>
    private lateinit var sourateAdapter : SourateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_sourates)

        listOfSourate = ArrayList()
        sourateAdapter = SourateAdapter(this, listOfSourate)

        sourateRecyclerView.adapter = sourateAdapter
        sourateRecyclerView.layoutManager = LinearLayoutManager(this)

        getData()
        sourateAdapter.notifyDataSetChanged()

    }

    private fun getData(): JSONObject {
        val inputStream : InputStream = assets.open("quran.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)

        try {
            val jsonArray = jsonObject.getJSONArray("sourates")

            for (i in 0..jsonArray.length()){
                val sourates = jsonArray.getJSONObject(i)
                val positionSourate = sourates.getInt("position")
                val frenchName = sourates.getString("nom_sourate")
                val phoneticName = sourates.getString("nom_phonetique")
                val arabicName = sourates.getString("nom")
                val sourateObject = SourateObject(positionSourate, frenchName,phoneticName, arabicName)

                listOfSourate.add(sourateObject)
            }
        }
        catch (e: Exception){
            Log.e("Error", e.toString())
        }
        return jsonObject
    }
}