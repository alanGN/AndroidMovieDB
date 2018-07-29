package com.example.alan_pc.androidmoviedb.presentation.home

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.example.alan_pc.androidmoviedb.R
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse
import com.example.alan_pc.androidmoviedb.presentation.general.EndlessRecyclerOnScrollListener
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralFragment
import com.example.alan_pc.androidmoviedb.presentation.home.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by ALAN-PC on 29/07/2018
 */
class HomeFragment : GeneralFragment(), HomeMvp.View {

    var page: Int = 1
    private lateinit var adapter: MovieAdapter

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
        presenter.getMoviesByName(page.toString(), "Batman") //First time

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                page += 1
                presenter.getMoviesByName(page.toString(), "Batman")
            }
        })
    }

    override fun initializeDependencies() {
        super.initializeDependencies()
        getApplicationComponent().plus(HomeModule(this)).inject(this)
    }


    override fun onResultError(errorString: String?) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubscribe()
    }
}