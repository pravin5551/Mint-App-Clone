package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import android.widget.Toast
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
import com.nero.mint.data.remote.OnItemClickListener
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse
import com.nero.mint.repository.Repository
import com.nero.mint.viewModel.MyViewModel
import com.nero.mint.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home),OnItemClickListener {

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

        navController=Navigation.findNavController(view)

        val repository = Repository()

        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)


        val GridLayoutManager = StaggeredGridLayoutManager(1, 0)
        SearchButtonsRecyclerView.layoutManager = GridLayoutManager
        buttonsAdapter = ButtonsAdapter(buttonsList,this)
        SearchButtonsRecyclerView.adapter = buttonsAdapter



        viewModel.getButtonData().observe(requireActivity(), Observer {

            buttonsList.clear()
            buttonsList.addAll(it)
            buttonsAdapter.notifyDataSetChanged()

        })


        val LlManager = LinearLayoutManager(this.context)
        homeFragmentRecyclerView.layoutManager = LlManager
        viewAdapter = NewsAdapter(articlesList,this)
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

                Toast.makeText(activity, "Refreshed", Toast.LENGTH_SHORT).show()
            }




    }

    override fun onSaved(articlesItem: ArticlesItem) {
        TODO("Not yet implemented")
    }

    override fun selected(articlesItem: ArticlesItem) {
        val bundle = bundleOf("url" to articlesItem.url)

        navController.navigate(R.id.action_homefragment_to_fullViewFragment,bundle)

    }

    override fun onButtonClicked(name: String) {

        val bundle = bundleOf("newsItem" to name)

        navController.navigate(R.id.action_homefragment_to_buttonsViewFragment,bundle)

    }

    override fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse) {
        TODO("Not yet implemented")
    }

    override fun onPremiumArticleSelected(dataItem: DataItem) {
        TODO("Not yet implemented")
    }
}






