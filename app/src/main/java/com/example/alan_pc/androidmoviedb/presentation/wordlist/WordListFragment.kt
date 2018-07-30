package com.example.alan_pc.androidmoviedb.presentation.wordlist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralFragment
import com.example.alan_pc.androidmoviedb.presentation.wordlist.adapter.WordsAdapter
import kotlinx.android.synthetic.main.fragment_words_list.*


/**
 * Created by ALAN-PC on 29/07/2018
 */
class WordListFragment : GeneralFragment() {

    private lateinit var adapter: WordsAdapter
    private var searchedWords = mutableListOf<String>()


    override fun getLayoutId(): Int {
        return R.layout.fragment_words_list
    }

    companion object {
        private val EXTRA_WORDS = "WordListFragment.EXTRA_WORDS"

        fun newInstance(words: ArrayList<String>?): WordListFragment {
            val fragment = WordListFragment()
            val args = Bundle()
            args.putSerializable(EXTRA_WORDS, words)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            searchedWords = arguments!!.getStringArrayList(EXTRA_WORDS)
        }
    }

    override fun initResources() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        adapter = WordsAdapter(searchedWords)
        recyclerView.adapter = adapter
    }


}