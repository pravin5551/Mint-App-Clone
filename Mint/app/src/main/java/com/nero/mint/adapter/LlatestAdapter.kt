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
import com.nero.mint.newsPojo.ArticlesItem

class LlatestAdapter(  val articlesList: MutableList<ArticlesItem>,val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<LatestViewHolder>()  {
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
            container.setOnClickListener(View.OnClickListener {

                onItemClickListener.selected(articlesList[position])

            })

            selectButton.setOnClickListener(View.OnClickListener {
                selectedButton.visibility= View.VISIBLE
                selectButton.visibility=View.INVISIBLE

                onItemClickListener.addBookmarks(articlesList[position])

            })

            selectedButton.setOnClickListener(View.OnClickListener {

                selectButton.visibility= View.VISIBLE
                selectedButton.visibility=View.INVISIBLE

                onItemClickListener.deleteBookmarks(articlesList[position])

            })

        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}

class LatestViewHolder(val view: View):RecyclerView.ViewHolder(view) {
    val Image = view.findViewById<ImageView>(R.id.IvLatestNews)
    val Title = view.findViewById<TextView>(R.id.TvlatestHeadine)
    val Date = view.findViewById<TextView>(R.id.latestNewsDate)
    val container =view.findViewById<RelativeLayout>(R.id.RlLatestContainer)
    val selectButton = view.findViewById<ImageView>(R.id.latestNewsSaveBookmark)
    val selectedButton = view.findViewById<ImageView>(R.id.latestNewsSavedBookmark)

}
