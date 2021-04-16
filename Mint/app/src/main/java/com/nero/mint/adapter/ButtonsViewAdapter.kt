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


class ButtonsViewAdapter(val articlesList: MutableList<DataItem>) :RecyclerView.Adapter<ButtonsViewViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonsViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.buttons_full_view_item_layout,parent,false)
        return ButtonsViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ButtonsViewViewHolder, position: Int) {

        Glide.with(holder.Image).load(articlesList[position].imageUrl).into(holder.Image)
        holder.apply {
            Title.text = articlesList[position].title
            Description.text = articlesList[position].content
            Date.text = articlesList[position].date
        }
    }


    }


class ButtonsViewViewHolder(val view: View):RecyclerView.ViewHolder(view){


    val Image = view.findViewById<ImageView>(R.id.buttnsFullViewDisplayIv)
    val Title = view.findViewById<TextView>(R.id.buttnsFullViewTitleTv)
    val Description = view.findViewById<TextView>(R.id.buttnsFullViewDescTv)
    val Date = view.findViewById<TextView>(R.id.buttnsFullViewDateTv)


}