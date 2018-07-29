package com.example.alan_pc.androidmoviedb.presentation.main

import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralActivity
import com.example.alan_pc.androidmoviedb.presentation.home.HomeFragment

/**
 * Created by ALAN-PC on 29/07/2018
 */
class MainActivity : GeneralActivity() {

    override fun initResources() {
        supportFragmentManager.beginTransaction().add(
                R.id.activityMainFrameLayout,
                HomeFragment(), "tvShow").commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}