package com.example.apipokemon

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemRecycler (private var itemList:  MutableList<ItemResponse> = mutableListOf()) : RecyclerView.Adapter<ItemRecycler.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(newItem: List<ItemResponse>) {
        itemList.addAll(newItem)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.ItemImage)
        val itemName: TextView = itemView.findViewById(R.id.ItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_container, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ItemResponse = itemList[position]

        val imageUrl = item.sprites.image

        Glide.with(holder.itemImage.context)
            .load(imageUrl)
            .into(holder.itemImage)

        holder.itemName.text = item.name
    }

}