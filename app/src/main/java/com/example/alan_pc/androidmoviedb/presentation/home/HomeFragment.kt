package com.example.alan_pc.androidmoviedb.presentation.home

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.R.id.show_words
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse
import com.example.alan_pc.androidmoviedb.presentation.general.EndlessRecyclerOnScrollListener
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralFragment
import com.example.alan_pc.androidmoviedb.presentation.home.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import android.app.Activity
import android.content.Context


/**
 * Created by ALAN-PC on 29/07/2018
 */
class HomeFragment : GeneralFragment(), HomeMvp.View {

    var page: Int = 1
    private lateinit var adapter: MovieAdapter
    private lateinit var searchedText: String
    private val searchedWords = mutableListOf<String>()

    override fun onResultOk(movieResponse: MovieResponse) {
        Log.d("PAGES", movieResponse.page.toString())
        Log.d("ITEMS", movieResponse.movieList.size.toString())

        if (page == 1) {
            adapter = MovieAdapter(movieResponse.movieList)
            recyclerView.adapter = adapter
        } else {
            adapter.addMovieToList(movieResponse.movieList)
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
                page += 1
                presenter.getMoviesByName(page.toString(), searchedText)
            }
        })

        searchBtn.setOnClickListener {
            searchedText = textToSearchEt.text.toString()
            if (!searchedText.isEmpty()) {
                searchedWords.add(searchedText)
                presenter.getMoviesByName(page.toString(), searchedText)
                hideKeyboard(context!!, view!!)
            }
        }

        textToSearchEt.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                if (!textToSearchEt.text.toString().isEmpty()) presenter.getMoviesByName(page.toString(), textToSearchEt.text.toString())
                hideKeyboard(context!!, view!!)
            }
            true
        }
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
            show_words -> if (searchedWords.size > 0) Toast.makeText(context, "OPEN ACTIVITY", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}