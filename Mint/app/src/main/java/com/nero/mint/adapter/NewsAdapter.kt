package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.nero.mint.R
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.newsPojo.ArticlesItem
import kotlinx.android.synthetic.main.home_news_item_layout.*
import org.jetbrains.anko.find


class NewsAdapter(
    val articlesList: MutableList<ArticlesItem>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_news_item_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.Image).load(articlesList[position].urlToImage).into(holder.Image)
        holder.apply {
            Title.text = articlesList[position].title
            Description.text = articlesList[position].description
            Date.text = articlesList[position].publishedAt
            Image.setOnClickListener(View.OnClickListener {

                itemClickListener.selected(articlesList[position])

            })

            readMore.setOnClickListener(View.OnClickListener {

                itemClickListener.selected(articlesList[position])

            })


            select.setOnClickListener(View.OnClickListener {
                selected.visibility=View.VISIBLE
                select.visibility= View.INVISIBLE

                itemClickListener.addBookmarks(articlesList[position])

            })


            selected.setOnClickListener(View.OnClickListener {
                select.visibility=View.VISIBLE
                selected.visibility= View.INVISIBLE

                itemClickListener.deleteBookmarks(articlesList[position])

            })



        }
    }
}

class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val Image = view.findViewById<ImageView>(R.id.homeNewsDisplayIv)
    val Title = view.findViewById<TextView>(R.id.homeNewsTitleTv)
    val Description = view.findViewById<TextView>(R.id.homeNewsDescTv)
    val Date = view.findViewById<TextView>(R.id.homeNewsDateTv)

    val readMore = view.findViewById<TextView>(R.id.homeNewsReadMoreTv)
    val select = view.findViewById<ImageView>(R.id.homeNewsSelectBookMarkIv)
    val selected = view.findViewById<ImageView>(R.id.homeNewsSelectedBookMarkIv)

    val container = view.findViewById<LinearLayout>(R.id.homeLlContainer)

}