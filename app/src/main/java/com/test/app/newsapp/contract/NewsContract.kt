package com.test.app.newsapp.contract

import com.test.app.newsapp.BasePresenter
import com.test.app.newsapp.BaseView
import com.test.app.newsapp.data.entitiy.News

interface NewsContract {

    interface View: BaseView<Presenter> {

        fun showNews(newsList: List<News>)

        fun fieldLoadNews(message: String?)

        fun openNewsInBrowser(link: String)

    }

    interface Presenter: BasePresenter {

        fun clickNews(news: News)

        fun refresh()
    }
}