package com.nero.mint.views
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.nero.mint.R
import kotlinx.android.synthetic.main.activity_web_view_wall_street_journal.*
import kotlinx.android.synthetic.main.fragment_full_view.*


class WebViewActivityWallStreetJournal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_wall_street_journal)


        if (WebViewwallstreetjournal !=null)
        {
            val webSettings = WebViewwallstreetjournal!!.settings
            webSettings.javaScriptEnabled=true
            WebViewwallstreetjournal!!.webViewClient = WebViewClient()
            WebViewwallstreetjournal!!.loadUrl("https://www.wsj.com/")

            WebViewwallstreetjournal!!.webViewClient = object :WebViewClient(){

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

                    wsjProgressBar.visibility = View.VISIBLE
                    super.onPageStarted(view, url, favicon)

                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    wsjProgressBar.visibility = View.GONE
                    super.onPageFinished(view, url)

                }
            }
        }
    }

    override fun onBackPressed() {
        if (WebViewwallstreetjournal!!.canGoBack()){
            WebViewwallstreetjournal!!.goBack()
        }else {
            super.onBackPressed()
        }
    }
}