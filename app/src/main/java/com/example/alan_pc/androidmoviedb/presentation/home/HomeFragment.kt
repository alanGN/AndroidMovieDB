package com.example.alan_pc.androidmoviedb.presentation.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.R.id.show_words
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.Movie
import com.example.alan_pc.androidmoviedb.presentation.general.EndlessRecyclerOnScrollListener
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralFragment
import com.example.alan_pc.androidmoviedb.presentation.home.adapter.MovieAdapter
import com.example.alan_pc.androidmoviedb.presentation.wordlist.WordListActivity
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject


/**
 * Created by ALAN-PC on 29/07/2018
 */
class HomeFragment : GeneralFragment(), HomeMvp.View {

    var page: Int = 1
    private var adapter: MovieAdapter? = null
    private var searchedText: String = ""
    private var searchedWords = mutableListOf<String>()
    private var LIST_STATE = "LIST_STATE"
    private var CURRENTLY_SEARCHED_WORD = "CURRENTLY_SEARCHED_WORD"
    private var SEARCHED_WORDS = "SEARCHED_WORDS"
    private var MOVIE_ADAPTER_LIST = "MOVIE_ADAPTER_LIST"
    private var PAGE = "PAGE"

    override fun onResultOk(movies: MutableList<Movie>, page: Int) {
        Log.d("PAGES", page.toString())
        if (page == 1) {
            if(adapter==null){
                adapter = MovieAdapter(movies)
                recyclerView.adapter = adapter
            }else{
                adapter?.addMovieToList(movies)
            }
        } else {
            adapter?.addMovieToList(movies)
        }
    }

    @Inject
    lateinit var presenter: HomeMvp.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initResources() {
        toolbar.title = "MOVIE INFO"
        val activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                if(page==1) initializeValues()
                page += 1
                presenter.getMoviesByName(page.toString(), searchedText)
            }
        })

        searchBtn.setOnClickListener {
            if (!textToSearchEt.text.toString().isEmpty()) {
                addWordsSearchAndClearAdapterIfNeeded()
                hideKeyboard(context!!, view!!)
            }
        }

        textToSearchEt.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                if (!textToSearchEt.text.toString().isEmpty()) {
                    addWordsSearchAndClearAdapterIfNeeded()
                }
                hideKeyboard(context!!, view!!)
            }
            true
        }
    }

    private fun addWordsSearchAndClearAdapterIfNeeded() {
        page = 1
        searchedText = textToSearchEt.text.toString()
        searchedWords.add(searchedText)
        if (adapter!=null){
            adapter?.clearAdapter()
        }
        presenter.getMoviesByName(page.toString(), searchedText)
    }

    private fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun initializeDependencies() {
        super.initializeDependencies()
        getApplicationComponent().plus(HomeModule(this)).inject(this)
    }


    override fun onResultError(errorString: String?) {
        Log.e("ERROR", errorString)
        Toast.makeText(activity, "Nothing found, search again", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            show_words -> if (searchedWords.size > 0) {
                val intent = WordListActivity.newIntent(activity!!, searchedWords)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Save state
        outState.putParcelable(LIST_STATE, recyclerView.layoutManager.onSaveInstanceState())
        outState.putString(CURRENTLY_SEARCHED_WORD, searchedText)
        outState.putStringArrayList(SEARCHED_WORDS, searchedWords as ArrayList<String>)
        outState.putParcelableArrayList(MOVIE_ADAPTER_LIST, ArrayList(adapter?.movieList))
        outState.putInt(PAGE,page)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            adapter = MovieAdapter(savedInstanceState.getParcelableArrayList(MOVIE_ADAPTER_LIST))
            recyclerView.adapter = adapter
            recyclerView.layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LIST_STATE))
            textToSearchEt.setText(savedInstanceState.getString(CURRENTLY_SEARCHED_WORD))
            searchedText = (savedInstanceState.getString(CURRENTLY_SEARCHED_WORD))
            searchedWords = (savedInstanceState.getStringArrayList(SEARCHED_WORDS))
            page = (savedInstanceState.getInt(PAGE))
        }
    }
}