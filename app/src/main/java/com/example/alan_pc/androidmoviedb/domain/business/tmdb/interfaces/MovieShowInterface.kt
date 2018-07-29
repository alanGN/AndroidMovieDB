package com.example.alan_pc.androidmoviedb.domain.business.tmdb.interfaces

import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse

/**
 * Created by ALAN-PC on 29/07/2018
 */
interface MovieInterface {

    interface getMoviesByName {
        fun getMoviesOk(movieResponse: MovieResponse)
    }

}