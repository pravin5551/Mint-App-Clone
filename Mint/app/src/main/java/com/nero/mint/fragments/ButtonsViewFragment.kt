package com.nero.mint.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nero.mint.R
import com.nero.mint.adapter.ButtonsViewAdapter
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_buttons_view.*
import kotlinx.android.synthetic.main.fragment_premium.*

class ButtonsViewFragment : Fragment(R.layout.fragment_buttons_view),OnItemClickListener {


    var articlesList = mutableListOf<DataItem>()
    lateinit var viewModel: MyViewModel
    lateinit var viewAdapter : ButtonsViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     var search = arguments?.getString("newsItem")!!

        ButtonsViewTv.text=search


        val repository = Repository()

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)




        val LlManager = LinearLayoutManager(this.context)
        buttonsFullViewRecyclerview.layoutManager = LlManager
        viewAdapter = ButtonsViewAdapter(articlesList)
        buttonsFullViewRecyclerview.adapter = viewAdapter


        viewModel.callSearchButtonNews(search).observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data?.data!!)
            viewAdapter.notifyDataSetChanged()



        })




    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun onButtonClicked(name: String) {
        TODO("Not yet implemented")
    }

    override fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun onPremiumArticleSelected(dataItem: DataItem) {
        TODO("Not yet implemented")
    }


}