package com.nero.mint.fragments

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
import com.nero.mint.R
import com.nero.mint.adapter.SearchAdapter
import com.nero.mint.data.remote.DataBase.BookmarkEntity
import com.nero.mint.data.remote.DataBase.NewsArticlesDataBase
import com.nero.mint.data.remote.DataBase.NewsArticlesEntity
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.data.remote.SearchPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewFragment : Fragment(R.layout.fragment_search_view), OnItemClickListener {


    lateinit var articlesList: MutableList<ArticlesItem>
    lateinit var navController: NavController
    lateinit var viewAdapter: SearchAdapter
    lateinit var viewModel: MyViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")

        articlesList = mutableListOf()

        val newsDao =  NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext()).getNewsArticlesDao()
        val repository = Repository(newsDao)

        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)

        navController = Navigation.findNavController(view)

        val LlManager = LinearLayoutManager(this.context)
        searchFullViewRecyclerview.layoutManager = LlManager
        viewAdapter = SearchAdapter(articlesList, this)
        searchFullViewRecyclerview.adapter = viewAdapter


        viewModel.callSearchApi(name!!).observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data?.articles!!)
            viewAdapter.notifyDataSetChanged()

            lLProgressBar.visibility =View.GONE

        })



    }

    override fun onSaved(articlesItem: com.nero.mint.newsPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: com.nero.mint.newsPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun onselected(articlesItem: ArticlesItem) {
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

        navController.navigate(R.id.action_searchViewFragment_to_fullViewFragment, bundle)

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

    override fun addBookmarks(articlesItem: com.nero.mint.newsPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun addBookMark(articlesItem: ArticlesItem) {
        val bookmarkEntity = BookmarkEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem
                .url
        )

        viewModel.addBookmarks(bookmarkEntity)
        Toast.makeText(activity, "Added to My Read", Toast.LENGTH_SHORT).show()
    }

    override fun deleteBookmarks(articlesItem: com.nero.mint.newsPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteBookMark(articlesItem: ArticlesItem) {
        val bookmarkEntity = BookmarkEntity(
            articlesItem.title,
            articlesItem.description,
            articlesItem.urlToImage,
            articlesItem.publishedAt,
            articlesItem
                .url
        )

        viewModel.deleteBookmarks(bookmarkEntity)
        Toast.makeText(activity, "Bookmark Removed", Toast.LENGTH_SHORT).show()

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

    override fun shareArticle(articlesItem: com.nero.mint.newsPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }


}