package com.example.alan_pc.androidmoviedb.presentation.general.reactive

import com.example.alan_pc.androidmoviedb.presentation.general.reactive.GeneralSubscriber
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by ALAN-PC on 29/07/2018
 */

interface GeneralBo {
    fun <T> execute(observable: Observable<T>, consumer: Consumer<T>): Observable<T>

    fun <T, K> execute(observable: Observable<T>, disposableObserver: GeneralSubscriber<T, K>): Observable<T>

    fun <T, K> execute(view: GeneralView, observable: Observable<T>, disposableObserver: GeneralSubscriber<T, K>): Observable<T>


    fun unSubscribe()
}