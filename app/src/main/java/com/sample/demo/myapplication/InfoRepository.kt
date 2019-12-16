package com.sample.demo.myapplication

import io.reactivex.Single
import tw.com.test.retrofitdemo.ApiService
import tw.com.test.retrofitdemo.AppClientManager
import tw.com.test.retrofitdemo.Posts


class InfoRepository {
    private val apiService = AppClientManager.client.create(ApiService::class.java)
    fun loadInfo(): Single<List<Posts>> {
        return apiService.getPosts()
    }
}