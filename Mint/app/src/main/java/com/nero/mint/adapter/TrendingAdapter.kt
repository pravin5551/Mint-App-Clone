package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse

class TrendingAdapter(val articlesList: MutableList<NewArticlesResponse>,
val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TrendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trending_item_layout, parent, false)
        return TrendingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {

        Glide.with(holder.Image).load(articlesList[position].image1).into(holder.Image)

        holder.apply {

            Title.text = articlesList[position].mainheading
            Date.text = articlesList[position].regid
            container.setOnClickListener(View.OnClickListener {

                onItemClickListener.onTrendingArticleSelected(articlesList[position])

            })

            SaveBookmark.setOnClickListener(View.OnClickListener {

                SavedBookmark.visibility=View.VISIBLE
                SaveBookmark.visibility=View.INVISIBLE

                onItemClickListener.addBookmarks(articlesList[position])

            })


            SavedBookmark.setOnClickListener(View.OnClickListener {

                SaveBookmark.visibility=View.VISIBLE
                SavedBookmark.visibility=View.INVISIBLE

                onItemClickListener.deleteBookmarks(articlesList[position])


            })



        }


    }
}

class TrendingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    val Image = view.findViewById<ImageView>(R.id.trendingNewsIv)
    val Title = view.findViewById<TextView>(R.id.trendingNewsTv)
    val Date = view.findViewById<TextView>(R.id.trendingNewsDate)
    val SaveBookmark = view.findViewById<ImageView>(R.id.trendingNewsSaveBookmark)
    val SavedBookmark = view.findViewById<ImageView>(R.id.trendingNewsSavedBookmark)
    val container = view.findViewById<RelativeLayout>(R.id.RlTrendingItemContainer)


}