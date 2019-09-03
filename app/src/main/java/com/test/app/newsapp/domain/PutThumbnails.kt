package com.test.app.newsapp.domain

import com.test.app.newsapp.UseCase
import com.test.app.newsapp.data.NewsRepository
import com.test.app.newsapp.data.entitiy.News
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PutThumbnails(private val newsRepository: NewsRepository) :
    UseCase<List<News>, Observable<News>> {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    override fun execute(i: List<News>?): Observable<News> {

        val subject: PublishSubject<News> = PublishSubject.create()
        var currThrowable: Throwable? = null
        i?.forEach {
            if (it.thumbnail == "") {
                Single.just(it)
                    .map { news -> newsRepository.putThumbnail(news) }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ data -> subject.onNext(data) }, { throwable ->
                        if (currThrowable == null) {
                            currThrowable = throwable
                            subject.onError(throwable)
                        }

                    })
                    .let { disposable -> compositeDisposable.add(disposable) }
            }
        }
        return subject.observeOn(AndroidSchedulers.mainThread())
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }

}