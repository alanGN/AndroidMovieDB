package com.example.alan_pc.androidmoviedb.presentation.general

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.alan_pc.androidmoviedb.BaseApplication
import com.example.alan_pc.androidmoviedb.di.component.ApplicationComponent

/**
 * Created by ALAN-PC on 29/07/2018
 */

abstract class GeneralActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initializeDependencies()
        if (savedInstanceState == null){
            initResources()
        }
    }

    /**
     * Template to set the layout ID
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * Template to set texts
     */
    protected abstract fun initResources()

    /**
     * Template to initialize dependencies
     */
    protected open fun initializeDependencies() {

    }


    /**
     * Retrieve the application component
     * @return
     */
    protected fun getApplicationComponent(): ApplicationComponent {
        return (applicationContext as BaseApplication).getApplicationComponent()
    }
}