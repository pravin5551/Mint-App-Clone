package com.nero.mint.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.nero.mint.R
import kotlinx.android.synthetic.main.fragment_full_view.*


class FullViewFragment : Fragment(R.layout.fragment_full_view) {

    var url: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        url = arguments?.getString("url")

        val webView = view.findViewById<WebView>(R.id.newsArticleWebView)
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        webView.loadUrl(url!!)
        webView.visibility = View.VISIBLE
        llProgressBarFullViewFragment.visibility = View.GONE

    }

}

