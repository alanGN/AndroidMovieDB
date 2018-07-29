package com.example.alan_pc.androidmoviedb.di.component

import com.example.alan_pc.androidmoviedb.BaseApplication
import com.example.alan_pc.androidmoviedb.di.module.ApplicationModule
import com.example.alan_pc.droidtv.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
abstract class ApplicationComponent {
    abstract fun inject(application: BaseApplication)
}