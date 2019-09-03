package com.test.app.newsapp.data.entitiy

data class News(

    val title: String,
    val description: String,
    val link: String,
    var thumbnail: String,
    val date: String


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as News

        if (title != other.title) return false
        if (description != other.description) return false
        if (link != other.link) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}

