package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nero.mint.R
import com.nero.mint.adapter.LlatestAdapter
import com.nero.mint.adapter.NewsAdapter
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_latest.*


class LatestFragment : Fragment(R.layout.fragment_latest) {

    lateinit var articlesList: MutableList<ArticlesItem>
    lateinit var viewAdapter: LlatestAdapter
    lateinit var viewModel: MyViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = Repository()
        articlesList = mutableListOf()
        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)


        val layoutManager = LinearLayoutManager(this.context)
        latestRecyclerview.layoutManager = layoutManager
        viewAdapter = LlatestAdapter(articlesList)
        latestRecyclerview.adapter = viewAdapter



        viewModel.callLatestNews().observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data!!.articles)
            viewAdapter.notifyDataSetChanged()


        })
    }


}