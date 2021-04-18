package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nero.mint.R
import com.nero.mint.adapter.LlatestAdapter
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
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LatestFragment : Fragment(R.layout.fragment_latest), OnItemClickListener {


    lateinit var articlesList: MutableList<ArticlesItem>
    lateinit var viewAdapter: LlatestAdapter
    lateinit var viewModel: MyViewModel
    lateinit var navController: NavController

    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO

    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()

        navController = Navigation.findNavController(view)

        val repository = Repository(newsDao)
        articlesList = mutableListOf()
        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)

        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.latestSwipeRefresh)

        val layoutManager = LinearLayoutManager(this.context)
        latestRecyclerview.layoutManager = layoutManager
        viewAdapter = LlatestAdapter(articlesList, this)
        latestRecyclerview.adapter = viewAdapter



        viewModel.callLatestNews().observe(requireActivity(), Observer {

            shimmerFrameLayoutLatestNews.stopShimmer()
            shimmerFrameLayoutLatestNews.visibility = View.GONE
            latestRecyclerview.visibility = View.VISIBLE
            articlesList.clear()
            articlesList.addAll(it.data!!.articles)
            viewAdapter.notifyDataSetChanged()


        })

        swipeRefreshLayout.setOnRefreshListener {

            viewModel.callLatestNews().observe(requireActivity(), Observer {

                swipeRefreshLayout.isRefreshing = true
                shimmerFrameLayoutLatestNews.stopShimmer()
                shimmerFrameLayoutLatestNews.visibility = View.GONE
                latestRecyclerview.visibility = View.VISIBLE
                articlesList.clear()
                articlesList.addAll(it.data!!.articles)
                viewAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false


            })
        }
    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {

        val bundle = bundleOf("url" to articlesItem.url )

        navController.navigate(R.id.action_latestfragment_to_fullViewFragment,bundle)

        val newsArticlesEntity = NewsArticlesEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem.url
        )


        CoroutineScope(Dispatchers.IO).launch {


            viewModel.addLatest(newsArticlesEntity)


        }
        latestAccountIv.setOnClickListener {
            navController.navigate(R.id.action_latestfragment_to_profileFragment)
        }

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


    override fun addBookmarks(articlesItem: ArticlesItem) {

        val bookmarkEntity = BookmarkEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem.url
        )

        CoroutineScope(Dispatchers.IO).launch {

            viewModel.addBookmarks(bookmarkEntity)

        }


    }

    override fun addBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(articlesItem: ArticlesItem) {

        val bookmarkEntity = BookmarkEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem.url
        )

        CoroutineScope(Dispatchers.IO).launch {

            viewModel.deleteBookmarks(bookmarkEntity)

        }
    }

    override fun deleteBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
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

    override fun shareArticle(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }


    override fun onResume() {
        super.onResume()
        shimmerFrameLayoutLatestNews.startShimmer()


    }
}