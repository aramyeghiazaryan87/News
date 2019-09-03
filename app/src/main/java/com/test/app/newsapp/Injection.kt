package com.test.app.newsapp

import com.test.app.newsapp.data.NewsRepository
import com.test.app.newsapp.data.source.remote.RemoteDataSource
import com.test.app.newsapp.data.source.remote.bbc.ApiBBC
import com.test.app.newsapp.data.source.remote.bbc.BBCClient
import com.test.app.newsapp.data.source.remote.bbc.BBCDataSource
import com.test.app.newsapp.data.source.remote.nesworg.ApiNewsOrg
import com.test.app.newsapp.data.source.remote.nesworg.NewsOrgClient
import com.test.app.newsapp.data.source.remote.nesworg.NewsOrgDataSource
import com.test.app.newsapp.domain.LoadNews
import com.test.app.newsapp.domain.PutThumbnails
import com.test.app.newsapp.presenter.NewsPresenter
import com.test.app.newsapp.view.adapter.NewsAdapter

class Injection {

    companion object {

         private val providerNewsRepository: NewsRepository by lazy {
            NewsRepository(
                RemoteDataSource.getInstance(
                    NewsOrgDataSource.getInstance(NewsOrgClient.get().create(ApiNewsOrg::class.java)),
                    BBCDataSource.getInstance(BBCClient.get().create(ApiBBC::class.java))
                )
            )
        }

        val providerLoadNews: LoadNews by lazy {
            LoadNews(providerNewsRepository)
        }

        val providerLoadThumbnails: PutThumbnails by lazy {
            PutThumbnails(providerNewsRepository)
        }

        val providerNewsAdapter: NewsAdapter by lazy {
            NewsAdapter()
        }

    }
}