package com.test.app.newsapp.data.entitiy

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "rss")
data class BBCLIst(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel

) {

    @Root(strict = false, name = "channel")
    data class Channel(

        @field:ElementList(inline = true)
        @param:ElementList(inline = true)
        val bbcList: List<Item>
    ) {

        @Root(strict = false, name = "item")
        data class Item(
            @field:Element(name = "link")
            @param:Element(name = "link")
            val link: String,
            @field:Element(name = "description")
            @param:Element(name = "description")
            val description: String,
            @field:Element(name = "title")
            @param:Element(name = "title")
            val title: String,
            @field:Element(name = "pubDate")
            @param:Element(name = "pubDate")
            val pubDate: String
        )
    }
}


