package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.newsPojo.DataItem

class PremiumAdapter(val articlesList: MutableList<DataItem>) : RecyclerView.Adapter<PremiumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.premium_item_layout, parent, false)
        return PremiumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: PremiumViewHolder, position: Int) {

        Glide.with(holder.Image).load(articlesList[position].imageUrl).into(holder.Image)

        holder.apply {

            Title.text=articlesList[position].title
            Date.text=articlesList[position].author

        }


    }
}

class PremiumViewHolder(val view: View): RecyclerView.ViewHolder(view){


    val Image = view.findViewById<ImageView>(R.id.premiumNewsIv)
    val Title = view.findViewById<TextView>(R.id.premiumNewsTv)
    val Date = view.findViewById<TextView>(R.id.premiumNewsDate)
    val SaveBookmark = view.findViewById<ImageView>(R.id.premiumNewsSaveBookmark)
    val SavedBookmark = view.findViewById<ImageView>(R.id.premiumNewsSavedBookmark)




}