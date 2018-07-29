package com.example.alan_pc.androidmoviedb.presentation.home

import com.example.alan_pc.androidmoviedb.di.ActivityScope
import dagger.Subcomponent

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Subcomponent(modules = arrayOf( HomeModule::class ))
@ActivityScope
interface HomeComponent {
    fun inject(fragment : HomeFragment)
}