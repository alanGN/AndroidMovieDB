package com.example.alan_pc.androidmoviedb.di.module

import android.app.Application
import android.app.Service
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Module
class ApplicationModule {
    private lateinit var application : Application
    private lateinit var service : Service
    private var context : Context

    constructor(application: Application){
        context = application.applicationContext
        this.application = application
    }

    constructor(service: Service){
        context = service.applicationContext
        this.service = service
    }

    @Provides
    @Singleton
    fun provideApplication() : Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext() : Context {
        return context
    }

    @Provides
    @Singleton
    fun provideResources() : Resources {
        return context.resources
    }
    
}