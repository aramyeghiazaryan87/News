package com.test.app.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.app.newsapp.R
import com.test.app.newsapp.data.entitiy.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {



    lateinit var callback: Callback


    var itemList: MutableList<News> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Glide.with(holder.image.context)
            .load(itemList[position].thumbnail)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(holder.image)

        holder.title.text = itemList[position].title
        holder.description.text = itemList[position].description

        holder.root.setOnClickListener { callback.onClick(itemList[position]) }
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val root: LinearLayout = view.root_news
        val image: ImageView = view.image
        val title: TextView = view.title
        val description: TextView = view.description
    }

    interface Callback {

        fun onClick(news: News)
    }
}