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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nero.mint.R
import com.nero.mint.adapter.BookMarksShortAdapter
import com.nero.mint.adapter.HistoryAdapter
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
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(R.layout.fragment_bookmark),OnItemClickListener {

    var bookmarksList = mutableListOf<BookmarkEntity>()
    var historyList= mutableListOf<NewsArticlesEntity>()
    lateinit var historyViewAdapter:HistoryAdapter
    lateinit var navController: NavController
    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO
    lateinit var viewModel:MyViewModel
    lateinit var viewAdapter: BookMarksShortAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController= Navigation.findNavController(view)
        MyReadsViewAllTv.setOnClickListener {

            navController.navigate(R.id.action_bookmarkFragment_to_bookMarkPreviewFragment)
        }

        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()


        val repository = Repository(newsDao)
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)



        val LlManager = StaggeredGridLayoutManager(1,0)
        bookMarksRecyclerView.layoutManager = LlManager
        viewAdapter = BookMarksShortAdapter(bookmarksList, this)
        bookMarksRecyclerView.adapter = viewAdapter

        viewModel.getBookmarksEntity().observe(requireActivity(), Observer {

            bookmarksList.clear()
            bookmarksList.addAll(it)
            viewAdapter.notifyDataSetChanged()

        })




        val llManager =LinearLayoutManager(requireContext())
        savedReadsRecyclerView.layoutManager = llManager
        historyViewAdapter = HistoryAdapter(historyList, this)
        savedReadsRecyclerView.adapter = historyViewAdapter

        viewModel.getNewsArticlesEntity().observe(requireActivity(), Observer {


            historyList.clear()
            historyList.addAll(it)
            historyViewAdapter.notifyDataSetChanged()



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

    override fun addBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteBookMarkEntity(bookmarkEntity: BookmarkEntity) {
        viewModel.deleteBookmarks(bookmarkEntity)
    }

    override fun selectBookMarkEntity(bookmarkEntity: BookmarkEntity) {

        val bundle = bundleOf("url" to bookmarkEntity.Url)

        navController.navigate(R.id.action_bookmarkFragment_to_fullViewFragment, bundle)
    }


    override fun selectArticleEntity(articlesEntity: NewsArticlesEntity) {

        val bundle = bundleOf("url" to articlesEntity.Url)

        navController.navigate(R.id.action_bookmarkFragment_to_fullViewFragment, bundle)


    }

    override fun shareArticle(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }


}