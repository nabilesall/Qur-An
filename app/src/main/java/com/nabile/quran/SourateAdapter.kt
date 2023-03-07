package com.nabile.quran

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class SourateAdapter(val context: Context, private val listOfSourate: ArrayList<SourateObject>, val jsonObject : JSONObject) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SourateViewHolder(
            LayoutInflater.from(context).inflate(R.layout.sourate_item_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfSourate.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sourate = listOfSourate[position]

        val sourateViewHolder = holder as SourateViewHolder
        sourateViewHolder.sourateIndex.text = sourate.positionSourate.toString() + ": "
        sourateViewHolder.sourateNameInFrench.text = sourate.frenchName +" ("+sourate.phoneticName+")"
        sourateViewHolder.sourateNameInArabic.text = sourate.arabicName

    }

    class SourateViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val sourateIndex = view.findViewById<TextView>(R.id.sourateIndex)
        val sourateNameInFrench = view.findViewById<TextView>(R.id.sourateNameInFrench)
        val sourateNameInArabic = view.findViewById<TextView>(R.id.sourateNameInArabic)

    }
}