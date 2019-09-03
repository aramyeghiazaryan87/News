package com.test.app.newsapp.data.entitiy

import com.google.gson.annotations.SerializedName

data class NewsOrgList(

    @SerializedName("articles")
    val newsOrgList: MutableList<Article>
) {
    data class Article(

        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String
    )
}


