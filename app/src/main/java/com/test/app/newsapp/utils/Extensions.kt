package com.test.app.newsapp.utils

fun <T> List<T>.combineList(l: List<T>): List<T> {
    val list: MutableList<T> = this.toMutableList()
    list.addAll(l)
    return list
}