package com.test.app.newsapp.domain

import com.test.app.newsapp.data.NewsRepository
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.UseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoadNews(private val newsRepository: NewsRepository) :
    UseCase<Unit, Single<List<News>>> {

    override fun execute(i: Unit?): Single<List<News>> =
        newsRepository.loadNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun dispose() {

    }
}