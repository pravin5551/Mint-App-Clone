package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.data.remote.DataBase.BookmarkEntity
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.fragments.BookmarkFragment

class BookMarksShortAdapter(
   val bookmarksList: MutableList<BookmarkEntity>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BookMarksShortViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarksShortViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmark_item_layout, parent, false)
        return BookMarksShortViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookmarksList.size
    }

    override fun onBindViewHolder(holder: BookMarksShortViewHolder, position: Int) {

        Glide.with(holder.Image).load(bookmarksList[position].Image).into(holder.Image)
        holder.apply {

            Title.text = bookmarksList[position].Title
            Date.text = bookmarksList[position].Date
            Image.setOnClickListener(View.OnClickListener {

                itemClickListener.selectBookMarkEntity(bookmarksList[position])

            })


            selected.setOnClickListener(View.OnClickListener {


                itemClickListener.deleteBookMarkEntity(bookmarksList[position])

            })


        }


    }

    fun getsize(): Int {
        return bookmarksList.size
    }


}


class BookMarksShortViewHolder(val view: View): RecyclerView.ViewHolder(view){



    val Image = view.findViewById<ImageView>(R.id.BookMarkMainIv)
    val Title = view.findViewById<TextView>(R.id.bookmarkTitleTv)
    val Date = view.findViewById<TextView>(R.id.bookmarkDateTv)
    val selected = view.findViewById<ImageView>(R.id.bookMarkRemoveIv)





}