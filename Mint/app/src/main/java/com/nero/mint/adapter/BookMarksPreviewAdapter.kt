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
import com.nero.mint.fragments.BookMarkPreviewFragment

class BookMarksPreviewAdapter(
    val bookmarksList: MutableList<BookmarkEntity>,
    val itemClickListener: OnItemClickListener
) :RecyclerView.Adapter<BookMarksPreviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarksPreviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmarked_item_layout, parent, false)
        return BookMarksPreviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookmarksList.size
    }

    override fun onBindViewHolder(holder: BookMarksPreviewViewHolder, position: Int) {

        Glide.with(holder.Image).load(bookmarksList[position].Image).into(holder.Image)
        holder.apply {
            Title.text = bookmarksList[position].Title
            Description.text = bookmarksList[position].Description
            Date.text = bookmarksList[position].Date
            Image.setOnClickListener(View.OnClickListener {

                itemClickListener.selectBookMarkEntity(bookmarksList[position])

            })

            readMore.setOnClickListener(View.OnClickListener {

                itemClickListener.selectBookMarkEntity(bookmarksList[position])

            })



            selected.setOnClickListener(View.OnClickListener {


                itemClickListener.deleteBookMarkEntity(bookmarksList[position])

            })


        }


    }
}

class BookMarksPreviewViewHolder(val view: View):RecyclerView.ViewHolder(view){


    val Image = view.findViewById<ImageView>(R.id.bookMarkedNewsDisplayIv)
    val Title = view.findViewById<TextView>(R.id.bookMarkedTitleTv)
    val Description = view.findViewById<TextView>(R.id.bookMarkedNewsDescTv)
    val Date = view.findViewById<TextView>(R.id.bookMarkedNewsDateTv)
    val readMore = view.findViewById<TextView>(R.id.bookMarkedReadMoreTv)
    val selected = view.findViewById<ImageView>(R.id.bookMarkedDeleteSelectedIv)





}