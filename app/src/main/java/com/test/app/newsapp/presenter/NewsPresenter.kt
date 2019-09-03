package com.test.app.newsapp.presenter


import android.util.Log
import com.test.app.newsapp.contract.NewsContract
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.domain.LoadNews
import com.test.app.newsapp.domain.PutThumbnails
import io.reactivex.disposables.CompositeDisposable

class NewsPresenter(
    private val view: NewsContract.View,
    private val loadNews: LoadNews,
    private val putThumbnails: PutThumbnails
) :
    NewsContract.Presenter {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var newsListSet: MutableSet<News> = linkedSetOf()
    private var newsList: MutableList<News> = arrayListOf()

    override fun clickNews(news: News) {
        view.openNewsInBrowser(news.link)
    }

    override fun refresh() {
        loadNews.execute(null)
            .map { it.sortedBy { news -> news.date } }
            .subscribe(
                {
                    newsList.addAll(it)
                    newsListSet.addAll(it)
                    view.showNews(it)
                    putThumbnails(it)
                },
                {
                    view.fieldLoadNews(it.message)
                }
            )
            .let { compositeDisposable.add(it) }
    }

    private fun putThumbnails(list: List<News>) {
        putThumbnails.execute(list)
            .subscribe(
                { news -> view.showNews(updateNewsList(news)) },
                { t -> view.fieldLoadNews(t.message) })
            .let { compositeDisposable.add(it) }
    }

    private fun updateNewsList(news: News): List<News> {
        newsListSet.add(news)
        newsList.clear()
        newsList.addAll(newsListSet)
        return newsList
    }

    override fun attach() {
        refresh()
    }

    override fun detach() {
        compositeDisposable.dispose()
        loadNews.dispose()
        putThumbnails.dispose()
    }

}
