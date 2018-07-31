package com.example.alan_pc.androidmoviedb.presentation.home

import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.TmdbBo
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.interfaces.MovieInterface
import javax.inject.Inject

/**
 * Created by ALAN-PC on 29/07/2018
 */
class HomePresenter @Inject constructor(var view: HomeMvp.View, var tmdbBo: TmdbBo) :
        HomeMvp.Presenter, MovieInterface.getMoviesByName {
    override fun getMoviesByName(page: String, textToSearch: String) {
        tmdbBo.getMovieList(view, this, page,textToSearch)
    }

    override fun getMoviesOk(movieResponse: MovieResponse) {
            view.onResultOk(movieResponse.movieList,movieResponse.page)
    }

    override fun unSubscribe() {
        tmdbBo.unSubscribe()
    }
}