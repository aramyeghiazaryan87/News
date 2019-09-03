package com.test.app.newsapp.data.source.remote.bbc.mapper

import com.test.app.newsapp.data.NewsMapper
import com.test.app.newsapp.data.entitiy.BBCLIst
import com.test.app.newsapp.data.entitiy.News
import com.test.app.newsapp.data.source.remote.bbc.parser.DateFormatParser

class BBCListToNewsListMapper : NewsMapper<BBCLIst.Channel.Item> {

    override fun map(t: BBCLIst.Channel.Item): News = News(
        t.title,
        t.description,
        t.link,
        "",
        DateFormatParser.parse(t.pubDate)
    )

}