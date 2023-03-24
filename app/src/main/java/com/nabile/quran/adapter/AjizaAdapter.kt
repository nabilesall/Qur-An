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
import com.nabile.quran.AjizaActivity
import com.nabile.quran.R
import com.nabile.quran.objects.AjizaObject

class AjizaAdapter(val context: Context, private val listOfAjiza: ArrayList<AjizaObject>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AjizaViewHolder(
            LayoutInflater.from(context).inflate(R.layout.ajiza_item_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfAjiza.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ajiza = listOfAjiza[position]

        val ajizaViewHolder = holder as AjizaViewHolder
        ajizaViewHolder.ajizaIndex.text = ajiza.positionAjiza.toString() + ": "
        ajizaViewHolder.ajizaNameInFrench.text = ajiza.ajizaFrenchName
        ajizaViewHolder.ajizaFromTo.text = ajiza.ajizaFromTo

        /*ajizaViewHolder.itemView.setOnClickListener {
            val intent = Intent(context, AjizaActivity::class.java)
            intent.putExtra("ajizaPosition",ajiza.positionAjiza.toString())
            intent.putExtra("title",ajiza.ajizaFrenchName)
            ContextCompat.startActivity(context, intent, null)
        }*/
    }

    class AjizaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ajizaIndex: TextView = view.findViewById(R.id.ajizaIndex)
        val ajizaNameInFrench: TextView = view.findViewById(R.id.ajizaNameInFrench)
        val ajizaFromTo: TextView = view.findViewById(R.id.ajizaFromTo)
    }
}