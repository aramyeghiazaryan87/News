package com.test.app.newsapp

interface BaseView<T : BasePresenter> {

    fun setPresenter(presenter: T)
}