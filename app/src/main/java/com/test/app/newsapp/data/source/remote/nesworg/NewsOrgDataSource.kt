package com.test.app.newsapp.data.source.remote.nesworg


import com.test.app.newsapp.data.NewsDataSource
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.source.remote.nesworg.mapper.NewsOrgListToNewsList
import com.test.app.newsapp.utils.Constants
import io.reactivex.Single

class NewsOrgDataSource(private val apiNewsOrg: ApiNewsOrg) : NewsDataSource {

    companion object {

        fun getInstance(apiNewsOrg: ApiNewsOrg): NewsOrgDataSource = NewsOrgDataSource(apiNewsOrg)
    }

    override fun getNews(): Single<List<News>> = apiNewsOrg.getArticles(
        Constants.DEFAULT_NEWS_ORG_SOURCE,
        Constants.NEWS_ORG_API_KEY
    )
        .map{it.newsOrgList}
        .map(NewsOrgListToNewsList())
}