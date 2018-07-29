package com.example.alan_pc.androidmoviedb.presentation.general.reactive

import android.support.annotation.CallSuper
import com.example.alan_pc.androidmoviedb.presentation.general.reactive.GeneralSubscriber
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by ALAN-PC on 29/07/2018
 */

open class GeneralBoImpl : GeneralBo {
    private var disposables : CompositeDisposable = CompositeDisposable()
    private var subscribed : Boolean = true

    override fun <T> execute(observable: Observable<T>, consumer: Consumer<T>): Observable<T> {
        val disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(consumer)
        disposables.add(disposable)
        return observable
    }

    override fun <T, K> execute(observable: Observable<T>, disposableObserver: GeneralSubscriber<T, K>): Observable<T> {
        val disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableObserver)
        disposables.add(disposable)
        return observable
    }

    override fun <T, K> execute(view: GeneralView, observable: Observable<T>, disposableObserver: GeneralSubscriber<T, K>): Observable<T> {
        disposableObserver.setView(view)
        val disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableObserver)
        disposables.add(disposable)
        return observable
    }

    @CallSuper
    override fun unSubscribe() {
        disposables.clear()
        subscribed = false
    }

}