package com.test.app.newsapp

interface UseCase<I, O> {

    fun execute(i: I?): O
    fun dispose()

}