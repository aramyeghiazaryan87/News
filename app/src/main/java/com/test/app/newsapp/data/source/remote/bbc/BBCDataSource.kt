package com.test.app.newsapp.data.source.remote.bbc

import com.test.app.newsapp.data.NewsDataSource
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.source.remote.bbc.mapper.BBCListToNewsListMapper
import io.reactivex.Single

class BBCDataSource(private val apiBBC: ApiBBC) : NewsDataSource {

    companion object {

        fun getInstance(apiBBC: ApiBBC): BBCDataSource = BBCDataSource(apiBBC)
    }

    override fun getNews(): Single<List<News>> = apiBBC.getChannel()
//        .flatMap{BBCRowListToBBCList.add(it)}
        .map { it.channel.bbcList }
        .map(BBCListToNewsListMapper())


}