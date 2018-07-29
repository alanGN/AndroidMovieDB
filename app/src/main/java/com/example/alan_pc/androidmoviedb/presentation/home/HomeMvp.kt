package com.example.alan_pc.androidmoviedb.presentation.home

import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralPresent
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView

/**
 * Created by ALAN-PC on 29/07/2018
 */
interface HomeMvp {

    interface View : GeneralView {
        fun onResultOk(movieResponse: MovieResponse)
    }

    interface Presenter : GeneralPresent {
        fun getMoviesByName(page: String, textToSearch: String)
    }
}