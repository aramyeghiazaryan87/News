package com.test.app.newsapp.data

import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.source.remote.RemoteDataSource
import io.reactivex.Single

class NewsRepository(private val remoteDataSource: RemoteDataSource) {

    companion object {

        fun getInstance(remoteDataSource: RemoteDataSource): NewsRepository =
            NewsRepository(remoteDataSource)
    }

    fun loadNews(): Single<List<News>> = remoteDataSource.getNews()

    fun putThumbnail(news: News): News = remoteDataSource.putThumbnail(news)
}