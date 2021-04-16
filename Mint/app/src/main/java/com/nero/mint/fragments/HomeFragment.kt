package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
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


        val repository = Repository()

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)


        val GridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsRecyclerView.layoutManager = GridLayoutManager
        buttonsAdapter = ButtonsAdapter(buttonsList)
        SearchButtonsRecyclerView.adapter = buttonsAdapter



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

        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)


        swipeRefreshLayout.setOnRefreshListener {


            viewModel.callBusinessApi().observe(requireActivity(), Observer {

                swipeRefreshLayout.isRefreshing = true
                articlesList.clear()
                articlesList.addAll(it.data!!.articles)
                viewAdapter.notifyDataSetChanged()

                swipeRefreshLayout.isRefreshing = false
            })



        }

    }
}






