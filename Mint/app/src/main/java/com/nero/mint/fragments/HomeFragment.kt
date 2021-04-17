package com.nero.mint.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nero.mint.R
import com.nero.mint.adapter.ButtonsAdapter
import com.nero.mint.adapter.NewsAdapter
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
import com.nero.mint.views.GoogleLogin
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener {

    var articlesList = mutableListOf<ArticlesItem>()
    lateinit var navController: NavController
    lateinit var viewAdapter: NewsAdapter
    lateinit var buttonsList: MutableList<String>
    lateinit var viewModel: MyViewModel
    lateinit var buttonsAdapter: ButtonsAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var isScrolling = false
    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()

        buttonsList = mutableListOf()
        navController = Navigation.findNavController(view)

        val repository = Repository(newsDao)

        buttonsList = mutableListOf()

        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        navController = Navigation.findNavController(view)


        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)


        val GridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsRecyclerView.layoutManager = GridLayoutManager
        buttonsAdapter = ButtonsAdapter(buttonsList, this)
        SearchButtonsRecyclerView.adapter = buttonsAdapter

        //for button viewModel

        viewModel.getButtonData().observe(requireActivity(), Observer {


            buttonsList.clear()
            buttonsList.addAll(it)
            buttonsAdapter.notifyDataSetChanged()

        })


        //manager
        val LlManager = LinearLayoutManager(this.context)
        homeFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = NewsAdapter(articlesList, this)
        homeFragmentRecyclerView.adapter = viewAdapter


        viewModel.callBusinessApi().observe(requireActivity(), Observer {

            shrimmerAndRecyclerViewVisible()
            articlesList.addAll(it.data!!.articles)
            viewAdapter.notifyDataSetChanged()
//            shimmer_view_container.stopShimmer()
        })


        swipeRefreshLayout.setOnRefreshListener {


            viewModel.callBusinessApi().observe(requireActivity(), Observer {

                swipeRefreshLayout.isRefreshing = true
                shrimmerAndRecyclerViewVisible()
                articlesList.addAll(it.data!!.articles)
                viewAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            })


            Toast.makeText(activity, "Refreshed", Toast.LENGTH_SHORT).show()
        }



        accountIv.setOnClickListener {
            val intent = Intent(activity, GoogleLogin::class.java)
            startActivity(intent)
        }

    }

    private fun shrimmerAndRecyclerViewVisible() {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.visibility = View.GONE
        homeFragmentRecyclerView.visibility = View.VISIBLE
        articlesList.clear()
    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }


    override fun selected(articlesItem: ArticlesItem) {

        val newsArticlesEntity = NewsArticlesEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem.url
        )

        CoroutineScope(Dispatchers.IO).launch {

            viewModel.insertArticles(newsArticlesEntity)


        }


        val bundle = bundleOf("url" to articlesItem.url)

        navController.navigate(R.id.action_homefragment_to_fullViewFragment, bundle)

    }

    override fun onButtonClicked(name: String) {

        val bundle = bundleOf("newsItem" to name)

        navController.navigate(R.id.action_homefragment_to_buttonsViewFragment, bundle)

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
            articlesItem
                .url
        )

        viewModel.addBookmarks(bookmarkEntity)

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
            articlesItem
                .url
        )

        viewModel.deleteBookmarks(bookmarkEntity)

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
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                articlesItem.title + "\n" +
                        articlesItem.url
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()

    }

    override fun onStop() {
        super.onStop()
        shimmerFrameLayout.stopShimmer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        shimmerFrameLayout.stopShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout.stopShimmer()
    }


}






