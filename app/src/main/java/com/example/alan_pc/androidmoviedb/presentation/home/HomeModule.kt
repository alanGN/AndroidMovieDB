package com.example.alan_pc.androidmoviedb.presentation.home

import com.example.alan_pc.androidmoviedb.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Module
class HomeModule(private var view: HomeMvp.View) {

    @Provides
    @ActivityScope
    internal fun provideView(): HomeMvp.View {
        return view
    }

    @Provides
    @ActivityScope
    internal fun providePresenter(presenter : HomePresenter): HomeMvp.Presenter {
        return presenter
    }
}