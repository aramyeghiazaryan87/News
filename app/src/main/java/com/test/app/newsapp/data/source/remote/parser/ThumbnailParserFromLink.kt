package com.test.app.newsapp.data.source.remote.parser

import com.test.app.newsapp.data.entitiy.News
import org.jsoup.Jsoup

class ThumbnailParserFromLink {

    companion object {

        fun parse(news: News): News {

            for (meta in Jsoup.connect(news.link).get().head().getElementsByTag("meta")) {
                if (meta.attr("property") == "og:image") {
                    meta.attr("content")?.let {
                        news.thumbnail = it
                    }
                    return news
                }
            }
            return news
        }
    }

}