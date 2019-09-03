package com.test.app.newsapp.data.source.remote.nesworg

import com.test.app.newsapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsOrgClient {

    companion object {

        fun get(): Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL_NEWS_ORG)
                .build()
        }
    }
}