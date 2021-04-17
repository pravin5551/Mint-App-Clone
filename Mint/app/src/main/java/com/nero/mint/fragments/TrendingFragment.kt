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
import com.nero.mint.data.remote.DataBase.BookmarkEntity
import com.nero.mint.data.remote.DataBase.NewsArticlesDataBase
import com.nero.mint.data.remote.DataBase.NewsArticlesEntity
import com.nero.mint.data.remote.DataBase.NewsDAO
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
<<<<<<< HEAD
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
=======
    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO
>>>>>>> 2c92990363b17324d805af278292841e7f1f0695


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
        navController = Navigation.findNavController(view)
        buttonsList = mutableListOf()
        val repository = Repository()
=======

        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()

        navController = Navigation.findNavController(view)
        buttonsList = mutableListOf()
        val repository = Repository(newsDao)
>>>>>>> 2c92990363b17324d805af278292841e7f1f0695

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)


<<<<<<< HEAD
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeTrending)

=======
>>>>>>> 2c92990363b17324d805af278292841e7f1f0695
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

<<<<<<< HEAD
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
=======
>>>>>>> 2c92990363b17324d805af278292841e7f1f0695
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
<<<<<<< HEAD
=======


        val newsArticlesEntity = NewsArticlesEntity(
            newsArticlesResponse.mainheading,
            newsArticlesResponse.content,
            newsArticlesResponse.frontimage,
            newsArticlesResponse.author,
            newsArticlesResponse.tags
        )

        viewModel.addLatest(newsArticlesEntity)

>>>>>>> 2c92990363b17324d805af278292841e7f1f0695

    }

    override fun onPremiumArticleSelected(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(newsArticlesResponse: NewArticlesResponse) {

        val bookmarkEntity = BookmarkEntity(
            newsArticlesResponse.mainheading,
            newsArticlesResponse.content,
            newsArticlesResponse.frontimage,
            newsArticlesResponse.author,
            newsArticlesResponse.tags
        )

        viewModel.addBookmarks(bookmarkEntity)

    }

    override fun deleteBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(newsArticlesResponse: NewArticlesResponse) {
        val bookmarkEntity = BookmarkEntity(
            newsArticlesResponse.mainheading,
            newsArticlesResponse.content,
            newsArticlesResponse.frontimage,
            newsArticlesResponse.author,
            newsArticlesResponse.tags
        )

        viewModel.deleteBookmarks(bookmarkEntity)

    }

    override fun deleteBookMarkEntity(bookmarkEntity: BookmarkEntity) {
        TODO("Not yet implemented")
    }

    override fun selectBookMarkEntity(bookmarkEntity: BookmarkEntity) {
        TODO("Not yet implemented")
    }

    override fun selectArticleEntity(articlesEntity: NewsArticlesEntity) {
        TODO("Not yet implemented")
    }

}