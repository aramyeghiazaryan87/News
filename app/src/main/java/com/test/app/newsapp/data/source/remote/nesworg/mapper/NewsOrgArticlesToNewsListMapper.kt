package com.test.app.newsapp.data.source.remote.nesworg.mapper

import com.test.app.newsapp.data.NewsMapper
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.entitiy.NewsOrgList

class NewsOrgListToNewsList : NewsMapper<NewsOrgList.Article> {
    override fun map(t: NewsOrgList.Article): News = News(t.title,
                t.description,
                t.url,
                t.urlToImage,
                t.publishedAt)
}