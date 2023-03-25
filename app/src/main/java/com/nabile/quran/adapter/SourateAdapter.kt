package com.nabile.quran.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nabile.quran.R
import com.nabile.quran.SourateActivity
import com.nabile.quran.objects.SourateObject

class SourateAdapter(val context: Context, private val listOfSourate: ArrayList<SourateObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

        sourateViewHolder.itemView.setOnClickListener {
            val intent = Intent(context, SourateActivity::class.java)
            intent.putExtra("sourate",sourate.positionSourate.toString())
            intent.putExtra("title",sourate.frenchName +" ("+sourate.phoneticName+")")
            intent.putExtra("verse","1")
            ContextCompat.startActivity(context, intent, null)
        }
    }

    class SourateViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val sourateIndex: TextView = view.findViewById(R.id.sourateIndex)
        val sourateNameInFrench: TextView = view.findViewById(R.id.sourateNameInFrench)
        val sourateNameInArabic: TextView = view.findViewById(R.id.sourateNameInArabic)
    }
}