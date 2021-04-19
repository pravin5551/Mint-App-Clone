package com.nero.mint.adapter


import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.data.remote.SearchPojo.ArticlesItem

class SearchAdapter(
    val articlesList: MutableList<ArticlesItem>,
    val itemClickListener: OnItemClickListener

) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item_layout, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(holder.Image).load(articlesList[position].urlToImage).into(holder.Image)
        holder.apply {

            Title.text = articlesList[position].title
            Description.text = articlesList[position].description
            Date.text = articlesList[position].publishedAt
            Image.setOnClickListener(View.OnClickListener {

                itemClickListener.onselected(articlesList[position])

            })

            readMore.setOnClickListener(View.OnClickListener {

                itemClickListener.onselected(articlesList[position])

            })


            select.setOnClickListener(View.OnClickListener {
                selected.visibility = View.VISIBLE
                select.visibility = View.INVISIBLE

                itemClickListener.addBookMark(articlesList[position])

            })


            selected.setOnClickListener(View.OnClickListener {
                select.visibility = View.VISIBLE
                selected.visibility = View.INVISIBLE

                itemClickListener.deleteBookMark(articlesList[position])

            })


        }
    }
}

class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val Image = view.findViewById<ImageView>(R.id.searchNewsDisplayIv)
    val Title = view.findViewById<TextView>(R.id.searchNewsTitleTv)
    val Description = view.findViewById<TextView>(R.id.searchNewsDescTv)
    val Date = view.findViewById<TextView>(R.id.searchNewsDateTv)
    val container = view.findViewById<LinearLayout>(R.id.searchLlContainer)
    val readMore = view.findViewById<ImageView>(R.id.searchReadMoreIv)
    val select = view.findViewById<ImageView>(R.id.searchSelectBookMarkIv)
    val selected = view.findViewById<ImageView>(R.id.searchSelectedBookMarkIv)


}