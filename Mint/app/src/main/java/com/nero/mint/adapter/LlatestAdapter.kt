package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.newsPojo.ArticlesItem

class LlatestAdapter(  val articlesList: MutableList<ArticlesItem>): RecyclerView.Adapter<LatestViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.latest_item_layout, parent, false)
        return LatestViewHolder(view)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        Glide.with(holder.Image).load(articlesList[position].urlToImage).into(holder.Image)
        holder.apply {

            Title.text = articlesList[position].title
            Date.text = articlesList[position].publishedAt
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}

class LatestViewHolder(val view: View):RecyclerView.ViewHolder(view) {
    val Image = view.findViewById<ImageView>(R.id.IvLatestNews)
    val Title = view.findViewById<TextView>(R.id.TvHeadine)
    val Date = view.findViewById<TextView>(R.id.TvDateNews)
}
