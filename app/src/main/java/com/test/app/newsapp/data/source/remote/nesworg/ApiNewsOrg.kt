package com.test.app.newsapp.data.source.remote.nesworg

import com.test.app.newsapp.data.entitiy.NewsOrgList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsOrg {

    @GET("/v2/top-headlines")
    fun getArticles(@Query("sources") sources: String,
                    @Query("apiKey") apiKey: String
    ): Single<NewsOrgList>
}