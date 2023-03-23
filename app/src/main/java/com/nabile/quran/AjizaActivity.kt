package com.nabile.quran

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabile.quran.adapter.VersesAdapter
import com.nabile.quran.objects.VerseObject
import kotlinx.android.synthetic.main.activity_sourate.*


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
}