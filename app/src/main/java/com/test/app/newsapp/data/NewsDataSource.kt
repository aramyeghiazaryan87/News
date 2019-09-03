package com.test.app.newsapp.data

import com.test.app.newsapp.data.entitiy.News
import io.reactivex.Single

interface NewsDataSource {

    fun getNews() : Single<List<News>>
}