package com.nabile.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var listOfSourate : ArrayList<SourateObject>
    lateinit var sourateAdapter : SourateAdapter
    lateinit var jsonObject : JSONObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfSourate = ArrayList()
        sourateAdapter = SourateAdapter(this, listOfSourate,getData())
        sourateRecyclerView.adapter = sourateAdapter
        sourateRecyclerView.layoutManager = LinearLayoutManager(this)

        sourateAdapter.notifyDataSetChanged()

    }

    private fun getData(): JSONObject {
        val inputStream : InputStream = assets.open("quran.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        var jsonObject = JSONObject(json)

        try {
            var jsonArray = jsonObject.getJSONArray("sourates")

            for (i in 0..jsonArray.length()){
                val sourates = jsonArray.getJSONObject(i)
                val positionSourate = sourates.getInt("position")
                val frenchName = sourates.getString("nom_sourate")
                val phoneticName = sourates.getString("nom_phonetique")
                val arabicName = sourates.getString("nom")
                var sourateObject = SourateObject(positionSourate, frenchName,phoneticName, arabicName)

                listOfSourate.add(sourateObject)
            }

        }
        catch (e: Exception){
            Log.e("Error", e.toString())
        }

        return jsonObject
    }

}