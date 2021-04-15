package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nero.mint.R
import com.nero.mint.adapter.TrendingAdapter
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_trending.*


class TrendingFragment : Fragment(R.layout.fragment_trending){

    var articlesList = mutableListOf<NewArticlesResponse>()
    lateinit var viewModel: MyViewModel
    lateinit var repository: Repository
    lateinit var viewAdapter:TrendingAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val repository= Repository()

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)




        val LlManager = LinearLayoutManager(this.context)
        trendingFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = TrendingAdapter(articlesList)
        trendingFragmentRecyclerView.adapter = viewAdapter


        viewModel.callTrendingApi().observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data!!)
            viewAdapter.notifyDataSetChanged()

        })




    }

}