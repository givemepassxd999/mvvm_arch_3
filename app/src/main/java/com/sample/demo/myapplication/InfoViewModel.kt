package com.sample.demo.myapplication

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoViewModel(var infoRepository: InfoRepository) : ViewModel() {
    var userInfoLiveData = MutableLiveData<String>()
    @SuppressLint("CheckResult")
    fun callInfo() {
        val result = StringBuffer()
        infoRepository
            .loadInfo()
            .subscribeOn(Schedulers.newThread())
            .map {
                val sb = StringBuffer()
                it.forEach { post ->
                    sb.append(post.body)
                    sb.append("\n")
                    sb.append("---------------------\n")
                }
                result.append(sb.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userInfoLiveData.setValue(it.toString())
            }, {
                userInfoLiveData.setValue(result.toString())
            })
    }
}