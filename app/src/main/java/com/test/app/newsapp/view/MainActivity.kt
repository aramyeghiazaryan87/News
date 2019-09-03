package com.test.app.newsapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.app.newsapp.Injection
import com.test.app.newsapp.R
import com.test.app.newsapp.contract.NewsContract
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.presenter.NewsPresenter
import com.test.app.newsapp.utils.Constants
import com.test.app.newsapp.view.adapter.NewsAdapter


class MainActivity : AppCompatActivity(), NewsContract.View, NewsAdapter.Callback {


    private val newsPresenter: NewsPresenter by lazy {
        NewsPresenter(this, Injection.providerLoadNews, Injection.providerLoadThumbnails)
    }

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeLayout = findViewById(R.id.refresh_layout)
        swipeLayout.setOnRefreshListener {
            newsPresenter.refresh()
        }

        newsRecyclerView = findViewById(R.id.news_list)

        newsAdapter = Injection.providerNewsAdapter
        newsAdapter.callback = this
        newsRecyclerView.adapter = newsAdapter

        if (savedInstanceState == null) {
          swipeLayout.isRefreshing = true
            newsPresenter.attach()
        }

    }

    override fun showNews(newsList: List<News>) {
        swipeLayout.isRefreshing = false
        newsAdapter.itemList = newsList.toMutableList()
    }

    override fun fieldLoadNews(message: String?) {
        swipeLayout.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun openNewsInBrowser(link: String) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
    }

    override fun setPresenter(presenter: NewsContract.Presenter) {

    }

    override fun onClick(news: News) {
        newsPresenter.clickNews(news)
    }

    override fun onDestroy() {
        super.onDestroy()
        newsPresenter.detach()
    }
}



