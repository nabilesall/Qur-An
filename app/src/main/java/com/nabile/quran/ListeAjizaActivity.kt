package com.nabile.quran

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabile.quran.adapter.AjizaAdapter
import com.nabile.quran.objects.AjizaObject
import kotlinx.android.synthetic.main.activity_liste_ajiza.*
import org.json.JSONObject
import java.io.InputStream

class ListeAjizaActivity : AppCompatActivity() {

    private lateinit var listOfAjiza : ArrayList<AjizaObject>
    private lateinit var ajizaAdapter : AjizaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_ajiza)

        listOfAjiza = ArrayList()
        ajizaAdapter = AjizaAdapter(this, listOfAjiza)

        ajizaRecyclerView.adapter = ajizaAdapter
        ajizaRecyclerView.layoutManager = LinearLayoutManager(this)

        getData()
        ajizaAdapter.notifyDataSetChanged()
    }


    /**
     * Get the list of Ajiza from the json file
     */
    private fun getData() {
        val inputStream : InputStream = assets.open("ajiza.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)

        try {
            val jsonArray = jsonObject.getJSONArray("ajiza")
            for (i in 0 until jsonArray.length()){
                val ajiza = jsonArray.getJSONObject(i)
                val positionAjiza = ajiza.getInt("position")
                val ajizaFrenchName = ajiza.getString("nom")
                val ajizaFromTo = ajiza.getString("fromTo")
                val ajizaObject = AjizaObject(positionAjiza, ajizaFrenchName, ajizaFromTo)

                listOfAjiza.add(ajizaObject)
            }
        }
        catch (e: Exception){
            Log.e("Error", e.toString())
        }
    }
}