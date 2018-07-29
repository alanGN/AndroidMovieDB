package com.example.alan_pc.droidtv.di

/**
 * Created by ALAN-PC on 29/07/2018
 */

class NamedProperties {

    init {
        throw AssertionError("NamedProperties can't be instantiated")
    }

    companion object {
        const val BASE_URL = "NamedBaseUrl"
        const val RETROFIT_BASE = "RetrofitBase"
    }
}