package com.test.app.newsapp.data.source.remote.bbc

import com.test.app.newsapp.data.entitiy.BBCLIst
import io.reactivex.Single
import retrofit2.http.GET

interface ApiBBC {

    @GET("news/video_and_audio/technology/rss.xml")
    fun getChannel(): Single<BBCLIst>
}