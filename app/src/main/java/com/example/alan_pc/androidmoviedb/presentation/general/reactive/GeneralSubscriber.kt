package com.example.alan_pc.androidmoviedb.presentation.general.reactive

import com.example.alan_pc.androidmoviedb.data.exception.ValidationModelException
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import java.lang.Exception

/**
 * Created by ALAN-PC on 29/07/2018
 */

abstract class GeneralSubscriber<T, K>(protected var viewComponent: GeneralView) : DisposableObserver<T>() {
    protected var result: K? = null

    override fun onComplete() {
        if (result != null) onSuccess(result!!)
        else onError(Exception("Response object is null"))
    }


    override fun onError(e: Throwable) {
        viewComponent.onResultError(e.message)
    }

    override fun onNext(t: T) {
        if (t != null) {
            if (isValid(t)) {
                result = mapperResponseDto(t)
            } else {
                onError(ValidationModelException(String.format("[%s] The object is not valid", t)))
            }
        }
    }

    fun setView(view: GeneralView) {
        this.viewComponent = view
    }

    protected abstract fun onSuccess(@NonNull k: K)

    protected abstract fun mapperResponseDto(@NonNull t: T): K

    protected abstract fun isValid(@NonNull t: T): Boolean

}