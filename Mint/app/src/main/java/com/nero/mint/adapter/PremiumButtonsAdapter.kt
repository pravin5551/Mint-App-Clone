package com.nero.mint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.nero.mint.R
import com.nero.mint.data.remote.OnItemClickListener


class PremiumButtonsAdapter(val buttonsList: MutableList<String>,val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PremiumButtonsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumButtonsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.premium_search_buttons_item_layout, parent, false)
        return PremiumButtonsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return buttonsList.size
    }

    override fun onBindViewHolder(holder: PremiumButtonsViewHolder, position: Int) {
        holder.NewsButton.setText(buttonsList[position])
        holder.NewsButton.setOnClickListener(View.OnClickListener {

            itemClickListener.onButtonClicked(buttonsList[position])
        })


    }


}

class PremiumButtonsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    val NewsButton = view.findViewById<Button>(R.id.premiumRvSearchBtn)


}