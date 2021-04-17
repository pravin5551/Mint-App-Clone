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
import com.nero.mint.adapter.ButtonsAdapter
import com.nero.mint.adapter.PremiumAdapter
import com.nero.mint.adapter.PremiumButtonsAdapter
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
import kotlinx.android.synthetic.main.fragment_premium.*
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PremiumFragment : Fragment(R.layout.fragment_premium), OnItemClickListener {


    var articlesList = mutableListOf<DataItem>()
    lateinit var viewModel: MyViewModel
    lateinit var repository: Repository
    lateinit var viewAdapter: PremiumAdapter
    lateinit var premiumButtonsAdapter: PremiumButtonsAdapter
    lateinit var buttonsList: MutableList<String>
    lateinit var navController: NavController
    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()

        navController = Navigation.findNavController(view)
        buttonsList = mutableListOf()
        val repository = Repository(newsDao)

        val ViewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MyViewModel::class.java)


        val GridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsPremiumRecyclerView.layoutManager = GridLayoutManager
        premiumButtonsAdapter = PremiumButtonsAdapter(buttonsList, this)
        SearchButtonsPremiumRecyclerView.adapter = premiumButtonsAdapter



        viewModel.getButtonData().observe(requireActivity(), Observer {

            buttonsList.clear()
            buttonsList.addAll(it)
            premiumButtonsAdapter.notifyDataSetChanged()

        })


        val LlManager = LinearLayoutManager(this.context)
        premiumFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = PremiumAdapter(articlesList, this)
        premiumFragmentRecyclerView.adapter = viewAdapter


        viewModel.callPremiumApi().observe(requireActivity(), Observer {


            articlesList.clear()
            articlesList.addAll(it.data?.data!!)
            viewAdapter.notifyDataSetChanged()

        })


    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun onButtonClicked(name: String) {

        val bundle = bundleOf("newsItem" to name)

        navController.navigate(R.id.action_premiumfragment_to_buttonsViewFragment, bundle)

    }

    override fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun onPremiumArticleSelected(dataItem: DataItem) {

        val bundle = bundleOf("url" to dataItem.url)

        navController.navigate(R.id.action_premiumfragment_to_fullViewFragment, bundle)

        val newsArticlesEntity= NewsArticlesEntity(dataItem.title,dataItem.content,dataItem.imageUrl,dataItem.date,dataItem.url)

        CoroutineScope(Dispatchers.IO).launch {

        viewModel.addLatest(newsArticlesEntity)


        }


    }

    override fun addBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun addBookmarks(dataItem: DataItem) {


        val bookmarkEntity= BookmarkEntity(dataItem.title,dataItem.content,dataItem.imageUrl,dataItem.date,dataItem.url)

        CoroutineScope(Dispatchers.IO).launch {

            viewModel.addBookmarks(bookmarkEntity)


        }


    }

    override fun addBookmarks(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookmarks(dataItem: DataItem) {


        val bookmarkEntity= BookmarkEntity(dataItem.title,dataItem.content,dataItem.imageUrl,dataItem.date,dataItem.url)

        CoroutineScope(Dispatchers.IO).launch {

            viewModel.deleteBookmarks(bookmarkEntity)


        }



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

}