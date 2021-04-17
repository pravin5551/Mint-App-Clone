package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nero.mint.R
import com.nero.mint.adapter.PremiumButtonsAdapter
import com.nero.mint.adapter.TrendingAdapter
import com.nero.mint.adapter.TrendingButtonsAdapter
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.android.synthetic.main.fragment_premium.*
import kotlinx.android.synthetic.main.fragment_trending.*


class TrendingFragment : Fragment(R.layout.fragment_trending), OnItemClickListener {

    var articlesList = mutableListOf<NewArticlesResponse>()
    lateinit var viewModel: MyViewModel
    lateinit var repository: Repository
    lateinit var viewAdapter: TrendingAdapter
    lateinit var trendingButtonsAdapter: TrendingButtonsAdapter
    lateinit var buttonsList: MutableList<String>
    lateinit var navController: NavController
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        buttonsList = mutableListOf()
        val repository = Repository()

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)


        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeTrending)

        val GridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsTrendingRecyclerView.layoutManager = GridLayoutManager
        trendingButtonsAdapter = TrendingButtonsAdapter(buttonsList, this)
        SearchButtonsTrendingRecyclerView.adapter = trendingButtonsAdapter



        viewModel.getButtonData().observe(requireActivity(), Observer {

            buttonsList.clear()
            buttonsList.addAll(it)
            trendingButtonsAdapter.notifyDataSetChanged()

        })


        val LlManager = LinearLayoutManager(this.context)
        trendingFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = TrendingAdapter(articlesList, this)
        trendingFragmentRecyclerView.adapter = viewAdapter


        viewModel.callTrendingApi().observe(requireActivity(), Observer {

            shrimmerDisplay()
            articlesList.addAll(it.data!!)
            viewAdapter.notifyDataSetChanged()
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.callTrendingApi().observe(requireActivity(), Observer {

                swipeRefreshLayout.isRefreshing = true
                shrimmerDisplay()
                articlesList.addAll(it.data!!)
                viewAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            })
        }
    }

    private fun shrimmerDisplay() {
        shimmerFrameLayoutTrendingNews.stopShimmer()
        shimmerFrameLayoutTrendingNews.visibility = View.GONE
        trendingFragmentRecyclerView.visibility = View.VISIBLE
        articlesList.clear()
    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun onButtonClicked(name: String) {

        val bundle = bundleOf("newsItem" to name)

        navController.navigate(R.id.action_trending_to_buttonsViewFragment, bundle)


    }

    override fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse) {

        val bundle = bundleOf("url" to newsArticlesResponse.tags)

        navController.navigate(R.id.action_trending_to_fullViewFragment, bundle)

    }

    override fun onPremiumArticleSelected(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

}