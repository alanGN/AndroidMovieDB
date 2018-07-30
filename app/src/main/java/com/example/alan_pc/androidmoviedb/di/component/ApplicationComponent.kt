package com.example.alan_pc.androidmoviedb.di.component

import com.example.alan_pc.androidmoviedb.BaseApplication
import com.example.alan_pc.androidmoviedb.di.module.ApplicationModule
import com.example.alan_pc.androidmoviedb.di.module.NetworkModule
import com.example.alan_pc.androidmoviedb.presentation.home.HomeComponent
import com.example.alan_pc.androidmoviedb.presentation.home.HomeModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (NetworkModule::class)])
abstract class ApplicationComponent {
    abstract fun inject(application: BaseApplication)
    abstract fun plus(module: HomeModule): HomeComponent
}