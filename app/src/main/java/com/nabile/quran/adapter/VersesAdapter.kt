package com.nabile.quran.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nabile.quran.R
import com.nabile.quran.objects.VerseObject

class VersesAdapter (private val context: Context, private val listOfVerse: ArrayList<VerseObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VerseViewHolder(
            LayoutInflater.from(context).inflate(R.layout.verse_item_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfVerse.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val verse = listOfVerse[position]

        val verseViewHolder = holder as VerseViewHolder
        verseViewHolder.verseIndex.text = verse.positionVerse.toString()
        verseViewHolder.verseArabic.text = verse.verseInArabic
        verseViewHolder.verseFrench.text = verse.verseInFrench
    }

    class VerseViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val verseIndex: TextView = view.findViewById(R.id.verseIndex)
        val verseArabic: TextView = view.findViewById(R.id.verseInArabic)
        val verseFrench: TextView = view.findViewById(R.id.verseInFrench)
    }
}