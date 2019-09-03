package com.test.app.newsapp.data

import com.test.app.newsapp.data.entitiy.News
import io.reactivex.functions.Function


interface NewsMapper<T> : Function<List<T>, List<News>> {

    fun map(t: T) : News

    override fun apply(t: List<T>): List<News> {
        val returnValue = mutableListOf<News>()
        for (value in t) {
            returnValue.add(map(value))
        }
        return returnValue
    }

}