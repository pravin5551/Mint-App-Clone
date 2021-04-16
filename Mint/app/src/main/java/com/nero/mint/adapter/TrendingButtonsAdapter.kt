package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.nero.mint.R
import com.nero.mint.data.remote.OnItemClickListener


class TrendingButtonsAdapter(val buttonsList: MutableList<String>,
val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TrendingButtonsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingButtonsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trending_search_buttons_item_layout, parent, false)
        return TrendingButtonsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return buttonsList.size
    }

    override fun onBindViewHolder(holder:TrendingButtonsViewHolder, position: Int) {
        holder.NewsButton.setText(buttonsList[position])

        holder.NewsButton.setOnClickListener(View.OnClickListener {

            itemClickListener.onButtonClicked(buttonsList[position])

        })
    }


}

class TrendingButtonsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    val NewsButton = view.findViewById<Button>(R.id.trendingRvSearchBtn)


}