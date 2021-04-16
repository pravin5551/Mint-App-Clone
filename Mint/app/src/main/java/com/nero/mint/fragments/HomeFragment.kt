package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nero.mint.R
import com.nero.mint.adapter.ButtonsAdapter
import com.nero.mint.adapter.NewsAdapter
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_news_item_layout.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    var articlesList = mutableListOf<ArticlesItem>()
    lateinit var navController: NavController
    lateinit var viewAdapter: NewsAdapter
    lateinit var buttonsList: MutableList<String>
    lateinit var viewModel: MyViewModel
    lateinit var buttonsAdapter: ButtonsAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var isScrolling = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonsList = mutableListOf()

        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)


        val gridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsRecyclerView.layoutManager = gridLayoutManager
        buttonsAdapter = ButtonsAdapter(buttonsList)
        SearchButtonsRecyclerView.adapter = buttonsAdapter

        //for button viewModel

        viewModel.getButtonData().observe(requireActivity(), Observer {

            buttonsList.clear()
            buttonsList.addAll(it)
            buttonsAdapter.notifyDataSetChanged()

        })


        val LlManager = LinearLayoutManager(this.context)
        homeFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = NewsAdapter(articlesList)
        homeFragmentRecyclerView.adapter = viewAdapter


        viewModel.callBusinessApi().observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data!!.articles)
            viewAdapter.notifyDataSetChanged()

        })



        swipeRefreshLayout.setOnRefreshListener {


            viewModel.callBusinessApi().observe(requireActivity(), Observer {

                swipeRefreshLayout.isRefreshing = true

                articlesList.clear()
                articlesList.addAll(it.data!!.articles)
                viewAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false


                Toast.makeText(activity, "Refreshed", Toast.LENGTH_SHORT).show()
            })


        }

    }


}






