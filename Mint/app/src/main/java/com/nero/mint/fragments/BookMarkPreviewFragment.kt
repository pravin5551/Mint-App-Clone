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
import com.nero.mint.R
import com.nero.mint.adapter.BookMarksPreviewAdapter
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
import kotlinx.android.synthetic.main.fragment_book_mark_preview.*

class BookMarkPreviewFragment : Fragment(R.layout.fragment_book_mark_preview), OnItemClickListener {


    var bookmarksList = mutableListOf<BookmarkEntity>()
    lateinit var newsDb: NewsArticlesDataBase
    lateinit var newsDao: NewsDAO
    lateinit var viewModel: MyViewModel
    lateinit var viewAdapter: BookMarksPreviewAdapter
    lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = Navigation.findNavController(view)

        newsDb = NewsArticlesDataBase.getNewsArticlesDatabse(this.requireContext())
        newsDao = newsDb.getNewsArticlesDao()


        val repository = Repository(newsDao)
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)


        val LlManager = LinearLayoutManager(this.context)
        bookMarksPreviewRecyclerview.layoutManager = LlManager
        viewAdapter = BookMarksPreviewAdapter(bookmarksList, this)
        bookMarksPreviewRecyclerview.adapter = viewAdapter

        viewModel.getBookmarksEntity().observe(requireActivity(), Observer {

            bookmarksList.clear()
            bookmarksList.addAll(it)
            viewAdapter.notifyDataSetChanged()
            llProgressBarBookmarkPreview?.visibility = View.GONE
            if (viewAdapter.getsize() == 0) {
                bookMarksPreviewRecyclerview?.visibility = View.GONE
                tvBookmark?.visibility = View.VISIBLE
//                animationView.visibility = View.VISIBLE
            } else {
                bookMarksPreviewRecyclerview?.visibility = View.VISIBLE
                tvBookmark?.visibility = View.GONE
//                animationView.visibility = View.GONE
            }
        })


    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun onselected(articlesItem: com.nero.mint.data.remote.SearchPojo.ArticlesItem) {
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

    override fun addBookMark(articlesItem: com.nero.mint.data.remote.SearchPojo.ArticlesItem) {
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

    override fun deleteBookMark(articlesItem: com.nero.mint.data.remote.SearchPojo.ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun deleteBookMarkEntity(bookmarkEntity: BookmarkEntity) {
        viewModel.deleteBookmarks(bookmarkEntity)
    }

    override fun selectBookMarkEntity(bookmarkEntity: BookmarkEntity) {

        val bundle = bundleOf("url" to bookmarkEntity.Url)

        navController.navigate(R.id.action_homefragment_to_fullViewFragment, bundle)
    }

    override fun selectArticleEntity(articlesEntity: NewsArticlesEntity) {
        TODO("Not yet implemented")
    }

    override fun shareArticle(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }


}