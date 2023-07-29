package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MemeAdapter(val context : Context ) : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {

    private var list = ArrayList<Memes>()

    inner class MemeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val memeTitle : TextView = itemView.findViewById(R.id.memeTitle)
        val memeImage : ImageView = itemView.findViewById(R.id.memeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MemeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val current = list[position]
        holder.memeTitle.text = current.title
        holder.memeImage.load(current.memeImage)
    }


    fun updateMeme(list : List<Memes>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}