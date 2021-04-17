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
import com.nero.mint.data.remote.DataBase.NewsArticlesEntity
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.fragments.BookmarkFragment

class HistoryAdapter(
    val historyList: MutableList<NewsArticlesEntity>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HistoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_layout,parent,false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {


        Glide.with(holder.Image).load(historyList[position].Image).into(holder.Image)

        holder.apply {

            Title.text = historyList[position].Title
            Date.text = historyList[position].Date
            container.setOnClickListener(View.OnClickListener {

                itemClickListener.selectArticleEntity(historyList[position])

            })


        }


    }
}


class HistoryViewHolder(val view: View): RecyclerView.ViewHolder(view){


    val Image = view.findViewById<ImageView>(R.id.historyNewsIv)
    val Title = view.findViewById<TextView>(R.id.historyNewsTv)
    val Date = view.findViewById<TextView>(R.id.historyNewsDate)
    val container = view.findViewById<RelativeLayout>(R.id.RlHistoryItemContainer)



}