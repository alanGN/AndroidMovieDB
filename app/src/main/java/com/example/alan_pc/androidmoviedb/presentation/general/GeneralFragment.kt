package com.example.alan_pc.androidmoviedb.presentation.general

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alan_pc.androidmoviedb.BaseApplication
import com.example.alan_pc.androidmoviedb.di.component.ApplicationComponent


/**
 * Created by ALAN-PC on 29/07/2018
 */


abstract class GeneralFragment : Fragment() {

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View ?= null
        if (getLayoutId() != 0){
            view = inflater.inflate(getLayoutId(), container, false)
        }
        initializeDependencies()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResources()
    }

    /**
     * Template to set the layout ID
     */
    @LayoutRes protected abstract fun getLayoutId() : Int

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
        return (activity!!.applicationContext as BaseApplication).getApplicationComponent()
    }
}