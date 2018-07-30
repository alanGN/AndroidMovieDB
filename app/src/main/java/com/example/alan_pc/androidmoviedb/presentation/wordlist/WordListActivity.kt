package com.example.alan_pc.androidmoviedb.presentation.wordlist

import android.app.Activity
import android.content.Intent
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralActivity
import java.util.*


/**
 * Created by ALAN-PC on 29/07/2018
 */
class WordListActivity : GeneralActivity() {



    override fun initResources() {
        supportFragmentManager.beginTransaction().add(
                R.id.activityMainFrameLayout,
                WordListFragment.newInstance(intent.extras.getStringArrayList(EXTRA_WORDS)), "tvShow").commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    companion object {

        val EXTRA_WORDS = "WordListActivity.EXTRA_WORDS"

        fun newIntent(activity: Activity, words: MutableList<String>): Intent {
            val intent = Intent(activity, WordListActivity::class.java)
            intent.putStringArrayListExtra(EXTRA_WORDS, words as ArrayList<String>?)
            return intent
        }
    }

}