package com.example.doubtnutdemo.helper

import io.reactivex.Observable


interface RxBus {
    fun send(o:Any)
    fun <T> listen(eventType: Class<T>): Observable<T>
}