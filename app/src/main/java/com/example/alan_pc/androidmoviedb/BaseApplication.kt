package com.example.alan_pc.androidmoviedb

import android.app.Application
import com.example.alan_pc.androidmoviedb.di.component.ApplicationComponent
import com.example.alan_pc.androidmoviedb.di.module.ApplicationModule

/**
 * Created by ALAN-PC on 29/07/2018
 */
class BaseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        configureDependencies()
    }

    private fun configureDependencies() {
        applicationComponent = buildApplicationComponent()
        applicationComponent.inject(this)
    }

    fun buildApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    /**
     * Retrieve the application component
     *
     * @return @{link ApplicationComponent}
     */
    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}