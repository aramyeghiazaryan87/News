package com.test.app.newsapp.data.source.remote

import com.test.app.newsapp.data.NewsDataSource
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.source.remote.bbc.BBCDataSource
import com.test.app.newsapp.data.source.remote.parser.ThumbnailParserFromLink
import com.test.app.newsapp.data.source.remote.nesworg.NewsOrgDataSource
import com.test.app.newsapp.utils.combineList
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class RemoteDataSource(
    private val newsOrgDataSource: NewsOrgDataSource,
    private val bbcDataSource: BBCDataSource
) : NewsDataSource {

    companion object {

        fun getInstance(
            newsOrgDataSource: NewsOrgDataSource,
            bbcDataSource: BBCDataSource
        ): RemoteDataSource =
            RemoteDataSource(newsOrgDataSource, bbcDataSource)
    }

    override fun getNews(): Single<List<News>> =
        Single.zip(
            newsOrgDataSource.getNews(),
            bbcDataSource.getNews(),
            BiFunction { newsOrgList, bbcList -> newsOrgList.combineList(bbcList) })

    fun putThumbnail(news: News): News = ThumbnailParserFromLink.parse(news)

}